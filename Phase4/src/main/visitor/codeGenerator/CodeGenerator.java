package main.visitor.codeGenerator;

import main.ast.nodes.*;
import main.ast.nodes.declaration.*;
import main.ast.nodes.declaration.struct.*;
import main.ast.nodes.expression.*;
import main.ast.nodes.expression.operators.*;
import main.ast.nodes.expression.values.*;
import main.ast.nodes.expression.values.primitive.*;
import main.ast.nodes.statement.*;
import main.ast.types.*;
import main.ast.types.primitives.*;
import main.symbolTable.*;
import main.symbolTable.exceptions.*;
import main.symbolTable.items.FunctionSymbolTableItem;
import main.symbolTable.items.StructSymbolTableItem;
import main.visitor.Visitor;
import main.visitor.type.ExpressionTypeChecker;
import main.visitor.type.TypeChecker;

import java.io.*;
import java.util.*;

public class  CodeGenerator extends Visitor<String> {
    ExpressionTypeChecker expressionTypeChecker = new ExpressionTypeChecker();
    private String outputPath;
    private FileWriter currentFile;
    private Declaration currentFunction;
    private StructDeclaration currentStruct;
    private int tempVarSlot = 0;
    private int labelCounter = 0;
    private boolean isInStruct = false;
    private boolean hasReturn = false;

    private String STRUCT_PARENT = "java/lang/Object";
    private String int_TO_Int_COMMAND = "invokestatic java/lang/Integer/valueOf(I)Ljava/lang/Integer;";
    private String Int_TO_int_COMMAND = "invokevirtual java/lang/Integer/intValue()I";
    private String bool_TO_Bool_COMMAND = "invokestatic java/lang/Boolean/valueOf(Z)Ljava/lang/Boolean;";
    private String Bool_TO_bool_COMMAND = "invokevirtual java/lang/Boolean/booleanValue()Z";

    private void copyFile(String toBeCopied, String toBePasted) {
        try {
            File readingFile = new File(toBeCopied);
            File writingFile = new File(toBePasted);
            InputStream readingFileStream = new FileInputStream(readingFile);
            OutputStream writingFileStream = new FileOutputStream(writingFile);
            byte[] buffer = new byte[1024];
            int readLength;
            while ((readLength = readingFileStream.read(buffer)) > 0)
                writingFileStream.write(buffer, 0, readLength);
            readingFileStream.close();
            writingFileStream.close();
        } catch (IOException e) {//unreachable
        }
    }

    private void prepareOutputFolder() {
        this.outputPath = "output/";
        String jasminPath = "utilities/jarFiles/jasmin.jar";
        String listClassPath = "utilities/codeGenerationUtilityClasses/List.j";
        String fptrClassPath = "utilities/codeGenerationUtilityClasses/Fptr.j";
        try{
            File directory = new File(this.outputPath);
            File[] files = directory.listFiles();
            if(files != null)
                for (File file : files)
                    file.delete();
            directory.mkdir();
        }
        catch(SecurityException e) {//unreachable

        }
        copyFile(jasminPath, this.outputPath + "jasmin.jar");
        copyFile(listClassPath, this.outputPath + "List.j");
        copyFile(fptrClassPath, this.outputPath + "Fptr.j");
    }

    private void createFile(String name) {
        try {
            String path = this.outputPath + name + ".j";
            File file = new File(path);
            file.createNewFile();
            this.currentFile = new FileWriter(path);
        } catch (IOException e) {//never reached
        }
    }

    private void addCommand(String command) {
        try {
            command = String.join("\n\t\t", command.split("\n"));
            if(command.startsWith("Label_"))
                this.currentFile.write("\t" + command + "\n");
            else if(command.startsWith("."))
                this.currentFile.write(command + "\n");
            else
                this.currentFile.write("\t\t" + command + "\n");
            this.currentFile.flush();
        } catch (IOException e) {//unreachable

        }
    }

    private void addStaticMainMethod() {
        addCommand(".method public static main([Ljava/lang/String;)V");
        addCommand(".limit stack 128");
        addCommand(".limit locals 128");
        addCommand("new Main");
        addCommand("invokespecial Main/<init>()V");
        addCommand("return");
        addCommand(".end method");
    }

    private int slotOf(String identifier) {
        //todo: done:)
        int count = 1;
        if(currentFunction == null)
            return tempVarSlot++;
        if(currentFunction instanceof FunctionDeclaration) {
            for (VariableDeclaration varDeclaration : ((FunctionDeclaration) currentFunction).getArgs()) {
                if (varDeclaration.getVarName().getName().equals(identifier))
                    return count;
                count++;
            }
            if (((FunctionDeclaration) currentFunction).getBody() instanceof BlockStmt) {
                for (Statement statement : ((BlockStmt) ((FunctionDeclaration) currentFunction).getBody()).getStatements()) {
                    if (statement instanceof VarDecStmt) {
                        for (VariableDeclaration varDec : ((VarDecStmt) statement).getVars()) {
                            if (varDec.getVarName().getName().equals(identifier))
                                return count;
                            count++;
                        }
                    } else
                        break;
                }
            } else if (((FunctionDeclaration) currentFunction).getBody() instanceof VarDecStmt) {
                for (VariableDeclaration varDec : ((VarDecStmt) ((FunctionDeclaration) currentFunction).getBody()).getVars()) {
                    if (varDec.getVarName().getName().equals(identifier))
                        return count;
                    count++;
                }
            }
        }
        else if (currentFunction instanceof MainDeclaration) {
            if (((MainDeclaration) currentFunction).getBody() instanceof BlockStmt) {
                for (Statement statement : ((BlockStmt) ((MainDeclaration) currentFunction).getBody()).getStatements()) {
                    if (statement instanceof VarDecStmt) {
                        for (VariableDeclaration varDec : ((VarDecStmt) statement).getVars()) {
                            if (varDec.getVarName().getName().equals(identifier))
                                return count;
                            count++;
                        }
                    } else
                        break;
                }
            } else if (((MainDeclaration) currentFunction).getBody() instanceof VarDecStmt) {
                for (VariableDeclaration varDec : ((VarDecStmt) ((MainDeclaration) currentFunction).getBody()).getVars()) {
                    if (varDec.getVarName().getName().equals(identifier))
                        return count;
                    count++;
                }
            }
        }
        //first empty var
        return count + (tempVarSlot++);
    }

    @Override
    public String visit(Program program) {
        prepareOutputFolder();

        for(StructDeclaration structDeclaration : program.getStructs()){
            this.currentStruct = structDeclaration;
            structDeclaration.accept(this);
        }

        createFile("Main");

        this.currentFunction = program.getMain();
        program.getMain().accept(this);

        for (FunctionDeclaration functionDeclaration: program.getFunctions()){
            this.currentFunction = functionDeclaration;
            functionDeclaration.accept(this);
        }
        return null;
    }

    @Override
    public String visit(StructDeclaration structDeclaration) {
        try{
            String structKey = StructSymbolTableItem.START_KEY + structDeclaration.getStructName().getName();
            StructSymbolTableItem structSymbolTableItem = (StructSymbolTableItem)SymbolTable.root.getItem(structKey);
            SymbolTable.push(structSymbolTableItem.getStructSymbolTable());
        }catch (ItemNotFoundException e){//unreachable
        }
        createFile(structDeclaration.getStructName().getName());

        //todo: done:? list values
        String structName = structDeclaration.getStructName().getName();
        addCommand(".class public " + structName );
        addCommand(".super java/lang/Object\n ");

        structDeclaration.getBody().accept(this);
        addDefaultConstructor();

        SymbolTable.pop();
        return null;
    }

    @Override
    public String visit(FunctionDeclaration functionDeclaration) {
        try{
            String functionKey = FunctionSymbolTableItem.START_KEY + functionDeclaration.getFunctionName().getName();
            FunctionSymbolTableItem functionSymbolTableItem = (FunctionSymbolTableItem)SymbolTable.root.getItem(functionKey);
            SymbolTable.push(functionSymbolTableItem.getFunctionSymbolTable());
        }catch (ItemNotFoundException e){//unreachable
        }

        //todo: done:)
        String argsSignature = "";
        for(VariableDeclaration arg : functionDeclaration.getArgs())
            argsSignature += makeTypeSignature(arg.getVarType());
        addCommand(".method public " + functionDeclaration.getFunctionName().getName() + "(" + argsSignature + ")" + makeTypeSignature(functionDeclaration.getReturnType()));
        addCommand(".limit stack 128");
        addCommand(".limit locals 128");

        functionDeclaration.getBody().accept(this);
        if(!hasReturn)
            addCommand("return");
        else
            hasReturn = false;
        addCommand(".end method\n ");

        SymbolTable.pop();
        return null;
    }

    @Override
    public String visit(MainDeclaration mainDeclaration) {
        try{
            String functionKey = FunctionSymbolTableItem.START_KEY + "main";
            FunctionSymbolTableItem functionSymbolTableItem = (FunctionSymbolTableItem)SymbolTable.root.getItem(functionKey);
            SymbolTable.push(functionSymbolTableItem.getFunctionSymbolTable());
        }catch (ItemNotFoundException e){//unreachable
        }

        //todo: done:)
        String argsSignature = "";
        addCommand(".method public <init>(" + argsSignature + ")V");
        addCommand(".limit stack 128");
        addCommand(".limit locals 128");

        addCommand("invokespecial java/lang/Object/<init>()V");

        mainDeclaration.getBody().accept(this);
        if(!hasReturn)
            addCommand("return");
        else
            hasReturn = false;
        addCommand(".end method\n ");

        SymbolTable.pop();
        return null;
    }

    @Override
    public String visit(VariableDeclaration variableDeclaration) {
        //todo: done:)
        if (isInStruct) {
            addCommand(".field " + variableDeclaration.getVarName().getName()
                    + " " + makeTypeSignature(variableDeclaration.getVarType()));
        }
        else
            this.initializeVar(variableDeclaration, false);
        return null;
    }

    @Override
    public String visit(SetGetVarDeclaration setGetVarDeclaration) {
        return null;
    }

    @Override
    public String visit(AssignmentStmt assignmentStmt) {
        //todo: done:)
        String commands = this.visit(new BinaryExpression(assignmentStmt.getLValue(), assignmentStmt.getRValue(), BinaryOperator.assign));
        addCommand(commands);
        addCommand("pop");
        return null;
    }

    @Override
    public String visit(BlockStmt blockStmt) {
        //todo: done:)
        for (Statement statement: blockStmt.getStatements()) {
            statement.accept(this);
        }
        return null;
    }

    @Override
    public String visit(ConditionalStmt conditionalStmt) {
        //todo: done:?
        String elseLabel = getFreshLabel();
        String exitLabel = getFreshLabel();
        addCommand(conditionalStmt.getCondition().accept(this));
        addCommand("ifeq " + elseLabel);
        conditionalStmt.getThenBody().accept(this);
        //if(!(conditionalStmt.getThenBody().accept(this.typeChecker)).doesReturn)
        addCommand("goto " + exitLabel);
        addCommand(elseLabel + ":");
        if(conditionalStmt.getElseBody() != null)
            conditionalStmt.getElseBody().accept(this);
        addCommand(exitLabel + ":");
        return null;
    }

    @Override
    public String visit(FunctionCallStmt functionCallStmt) {
        //todo: done:)
        this.expressionTypeChecker.setInFunctionCallStmt(true);
        functionCallStmt.getFunctionCall().accept(this);
        this.expressionTypeChecker.setInFunctionCallStmt(false);
        addCommand("pop");
        return null;
    }

    @Override
    public String visit(DisplayStmt displayStmt) {
        addCommand("getstatic java/lang/System/out Ljava/io/PrintStream;");
        Type argType = displayStmt.getArg().accept(expressionTypeChecker);
        String commandsOfArg = displayStmt.getArg().accept(this);

        addCommand(commandsOfArg);
        if (argType instanceof IntType)
            addCommand("invokevirtual java/io/PrintStream/println(I)V");
        if (argType instanceof BoolType)
            addCommand("invokevirtual java/io/PrintStream/println(Z)V");

        return null;
    }

    @Override
    public String visit(ReturnStmt returnStmt) {
        //todo: done:)
        hasReturn = true;
        if (returnStmt.getReturnedExpr() == null)
            addCommand("return");
        else {
            Type type = returnStmt.getReturnedExpr().accept(expressionTypeChecker);
            addCommand(returnStmt.getReturnedExpr().accept(this));
            if (type instanceof IntType || type instanceof BoolType) {
                addCommand(convertPrimitiveToJavaObj(type));
            }
            addCommand("areturn");
        }
        return null;
    }

    @Override
    public String visit(LoopStmt loopStmt) {
        //todo
        return null;
    }

    @Override
    public String visit(VarDecStmt varDecStmt) {
        //todo: done:)
        for (VariableDeclaration varDec : varDecStmt.getVars()) {
            varDec.accept(this);
        }
        return null;
    }

    @Override
    public String visit(ListAppendStmt listAppendStmt) {
        //todo: done:)
        this.expressionTypeChecker.setInFunctionCallStmt(true);
        listAppendStmt.getListAppendExpr().accept(this);
        this.expressionTypeChecker.setInFunctionCallStmt(false);
        return null;
    }

    @Override
    public String visit(ListSizeStmt listSizeStmt) {
        //todo: done:)
        listSizeStmt.accept(this);
        addCommand("pop");
        return null;
    }

    @Override
    public String visit(BinaryExpression binaryExpression) {
        //todo
        return null;
    }

    @Override
    public String visit(UnaryExpression unaryExpression){
        return null;
    }

    @Override
    public String visit(StructAccess structAccess){
        //todo
        return null;
    }

    @Override
    public String visit(Identifier identifier){
        //todo
        return null;
    }

    @Override
    public String visit(ListAccessByIndex listAccessByIndex){
        //todo: done:)
        String commands = "";
        commands += listAccessByIndex.getInstance().accept(this) + "\n";
        commands += listAccessByIndex.getIndex().accept(this) + "\n";
        Type type = listAccessByIndex.accept(expressionTypeChecker);
        commands += "invokevirtual List/getElement(I)Ljava/lang/Object;\n";
        commands += castObject(type);
        if(type instanceof IntType)
            commands += "\n" + Int_TO_int_COMMAND;
        else if(type instanceof BoolType)
            commands += "\n" + Bool_TO_bool_COMMAND;
        return commands;
    }

    @Override
    public String visit(FunctionCall functionCall){
        //todo
        return null;
    }

    @Override
    public String visit(ListSize listSize){
        //todo
        return null;
    }

    @Override
    public String visit(ListAppend listAppend) {
        //todo
        return null;
    }

    @Override
    public String visit(IntValue intValue) {
        //todo: done:)
        return "ldc " + intValue.getConstant();
    }

    @Override
    public String visit(BoolValue boolValue) {
        //todo: done:)
        int boolIntVal = (boolValue.getConstant()) ? 1 : 0;
        return "ldc " + boolIntVal;
    }

    @Override
    public String visit(ExprInPar exprInPar) {
        return exprInPar.getInputs().get(0).accept(this);
    }

    private String getFreshLabel() {
        return "Label_" + this.labelCounter++;
    }

    private String convertPrimitiveToJavaObj(Type t) {
        if (t instanceof IntType)
            return int_TO_Int_COMMAND;
        else if (t instanceof BoolType)
            return bool_TO_Bool_COMMAND;

        return "";
    }

    private String castObject(Type t) {
        return "checkcast " + getClass(t);
    }

    private String getClass(Type t) {
        if(t instanceof ListType)
            return "List";
        else if(t instanceof IntType)
            return "java/lang/Integer";
        else if(t instanceof FptrType)
            return "Fptr";
        else if(t instanceof BoolType)
            return "java/lang/Boolean";
        else if(t instanceof StructType){
            return ((StructType) t).getStructName().getName();
        }
        return "";
    }

    private String makeTypeSignature(Type t) {
        if(t instanceof VoidType)
            return "V";
        else
            return "L" + getClass(t) + ";";
    }

    private void addDefaultConstructor() {
        addCommand(".method public <init>()V");
        addCommand(".limit stack 128");
        addCommand(".limit locals 128");
        addCommand("aload 0");
        addCommand("invokespecial java/lang/Object/<init>()V");
        Statement structBody = currentStruct.getBody();
        if (structBody instanceof BlockStmt) {
            for (Statement stmt: ((BlockStmt) structBody).getStatements()) {
                if (stmt instanceof VarDecStmt) {
                    for (VariableDeclaration varDec: ((VarDecStmt) stmt).getVars()) {
                        this.initializeVar(varDec, true);
                    }
                }

            }
        }
        else if (structBody instanceof Statement) {
            if (structBody instanceof VarDecStmt) {
                for (VariableDeclaration varDec: ((VarDecStmt) structBody).getVars()) {
                    this.initializeVar(varDec, true);
                }
            }
        }
        addCommand("return");
        addCommand(".end method\n ");
    }

    private void initializeVar(VariableDeclaration varDeclaration, boolean isField) {
        Type type = varDeclaration.getVarType();
        String name = varDeclaration.getVarName().getName();
        if(isField)
            addCommand("aload 0");
        addCommand(this.generateValue(varDeclaration.getDefaultValue(), type));
        if(type instanceof IntType)
            addCommand("invokestatic java/lang/Integer/valueOf(I)Ljava/lang/Integer;");
        else if(type instanceof BoolType)
            addCommand("invokestatic java/lang/Boolean/valueOf(Z)Ljava/lang/Boolean;");
        if(isField)
            addCommand("putfield " + currentStruct.getStructName().getName() + "/" + name + " " + makeTypeSignature(type));
        else
            addCommand("astore " + slotOf(varDeclaration.getVarName().getName()));
    }

    private String generateValue(Expression expr, Type type) {
        if(type instanceof IntType) {
            if(expr == null)
                return this.visit(new IntValue(0));
            else
                return expr.accept(this);
        }
        else if(type instanceof BoolType) {
            if(expr == null)
                return this.visit(new BoolValue(false));
            else
                return expr.accept(this);
        }
        else if(type instanceof StructType || type instanceof FptrType || type instanceof VoidType) {
            if(expr == null)
                return "aconst_null";
            else
                return expr.accept(this);
        }
        else if(type instanceof ListType) {
            String commands = "";
            commands += "new java/util/ArrayList\n";
            commands += "dup\n";
            commands += "invokespecial java/util/ArrayList/<Object>()V\n";
            int tempVar = slotOf("");
            commands += "astore " + tempVar + "\n";
        }
        return null;
    }
}
