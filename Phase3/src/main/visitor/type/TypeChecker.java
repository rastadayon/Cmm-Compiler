package main.visitor.type;

import main.ast.nodes.Program;
import main.ast.nodes.declaration.*;
import main.ast.nodes.declaration.struct.*;
import main.ast.nodes.statement.*;
import main.ast.types.ListType;
import main.ast.types.NoType;
import main.ast.types.Type;
import main.ast.types.primitives.BoolType;
import main.ast.types.primitives.IntType;
import main.compileError.typeError.UnsupportedTypeForDisplay;
import main.visitor.Visitor;

public class TypeChecker extends Visitor<Void> {
    ExpressionTypeChecker expressionTypeChecker;

    public void TypeChecker(){
        this.expressionTypeChecker = new ExpressionTypeChecker();
    }

    @Override
    public Void visit(Program program) {
        //Todo
        return null;
    }

    @Override
    public Void visit(FunctionDeclaration functionDec) {
        //Todo
        return null;
    }

    @Override
    public Void visit(MainDeclaration mainDec) {
        //Todo
        return null;
    }

    @Override
    public Void visit(VariableDeclaration variableDec) {
        //Todo
        return null;
    }

    @Override
    public Void visit(StructDeclaration structDec) {
        //Todo
        return null;
    }

    @Override
    public Void visit(SetGetVarDeclaration setGetVarDec) {
        //Todo
        return null;
    }

    @Override
    public Void visit(AssignmentStmt assignmentStmt) {
        //Todo
        return null;
    }

    @Override
    public Void visit(BlockStmt blockStmt) {
        //Todo
        return null;
    }

    @Override
    public Void visit(ConditionalStmt conditionalStmt) {
        //Todo
        return null;
    }

    @Override
    public Void visit(FunctionCallStmt functionCallStmt) {
        //Todo
        return null;
    }

    @Override
    public Void visit(DisplayStmt displayStmt) {
        //Todo
        Type argType = displayStmt.getArg().accept(expressionTypeChecker);
        if(!(argType instanceof IntType || argType instanceof ListType ||
                argType instanceof BoolType || argType instanceof NoType)) {
            UnsupportedTypeForDisplay exception = new UnsupportedTypeForDisplay(displayStmt.getLine());
            displayStmt.addError(exception);
        }
        return null; // not sure if this is right
    }

    @Override
    public Void visit(ReturnStmt returnStmt) {
        //Todo
        return null;
    }

    @Override
    public Void visit(LoopStmt loopStmt) {
        //Todo
        return null;
    }

    @Override
    public Void visit(VarDecStmt varDecStmt) {
        //Todo
        return null;
    }

    @Override
    public Void visit(ListAppendStmt listAppendStmt) {
        //Todo
        return null;
    }

    @Override
    public Void visit(ListSizeStmt listSizeStmt) {
        //Todo
        return null;
    }
}
