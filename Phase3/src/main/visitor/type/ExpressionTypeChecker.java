package main.visitor.type;

import main.ast.nodes.expression.*;
import main.ast.nodes.expression.operators.BinaryOperator;
import main.ast.nodes.expression.operators.UnaryOperator;
import main.ast.nodes.expression.values.primitive.BoolValue;
import main.ast.nodes.expression.values.primitive.IntValue;
import main.ast.types.*;
import main.ast.types.primitives.BoolType;
import main.ast.types.primitives.IntType;
import main.compileError.typeError.LeftSideNotLvalue;
import main.compileError.typeError.UnsupportedOperandType;
import main.visitor.Visitor;

public class ExpressionTypeChecker extends Visitor<Type> {

    @Override
    public Type visit(BinaryExpression binaryExpression) {
        //Todo: done:)
        BinaryOperator operator = binaryExpression.getBinaryOperator();
        Type firstType = binaryExpression.getFirstOperand().accept(this);
        Type secondType = binaryExpression.getSecondOperand().accept(this);
        if(operator == BinaryOperator.eq) {
            if(firstType instanceof NoType && secondType instanceof NoType) {
                return new NoType();
            }
            else if((firstType instanceof NoType && secondType instanceof ListType) ||
                    (secondType instanceof NoType && firstType instanceof ListType)) {
                UnsupportedOperandType exception = new UnsupportedOperandType(binaryExpression.getLine(), operator.name());
                binaryExpression.addError(exception);
                return new NoType();
            }
            else if(firstType instanceof NoType || secondType instanceof NoType) {
                return new NoType();
            }
            if(firstType instanceof IntType || firstType instanceof BoolType || firstType instanceof FptrType) {
                if (firstType.toString().equals(secondType.toString())) {
                    return new BoolType();
                }
            }
            if((firstType instanceof StructType && secondType instanceof StructType)) {
                if (((StructType) firstType).getStructName().getName().equals(((StructType) secondType).getStructName().getName())) {
                    return new BoolType();
                }
            }
        }
        if((operator == BinaryOperator.mult) || (operator == BinaryOperator.div) ||
                (operator == BinaryOperator.add) || (operator == BinaryOperator.sub)) {
            if(firstType instanceof NoType && secondType instanceof NoType) {
                return new NoType();
            }
            else if((firstType instanceof NoType && !(secondType instanceof IntType)) ||
                    (secondType instanceof NoType && !(firstType instanceof IntType))) {
                UnsupportedOperandType exception = new UnsupportedOperandType(binaryExpression.getLine(), operator.name());
                binaryExpression.addError(exception);
                return new NoType();
            }
            else if(firstType instanceof NoType || secondType instanceof NoType) {
                return new NoType();
            }
            if((firstType instanceof IntType) && (secondType instanceof IntType)) {
                return new IntType();
            }
        }
        if((operator == BinaryOperator.gt) || (operator == BinaryOperator.lt)) {
            if(firstType instanceof NoType && secondType instanceof NoType) {
                return new NoType();
            }
            else if((firstType instanceof NoType && !(secondType instanceof IntType)) ||
                    (secondType instanceof NoType && !(firstType instanceof IntType))) {
                UnsupportedOperandType exception = new UnsupportedOperandType(binaryExpression.getLine(), operator.name());
                binaryExpression.addError(exception);
                return new NoType();
            }
            else if(firstType instanceof NoType || secondType instanceof NoType) {
                return new NoType();
            }
            if((firstType instanceof IntType) && (secondType instanceof IntType)) {
                return new BoolType();
            }
        }
        if((operator == BinaryOperator.and) || (operator == BinaryOperator.or)) {
            if(firstType instanceof NoType && secondType instanceof NoType) {
                return new NoType();
            }
            else if((firstType instanceof NoType && !(secondType instanceof BoolType)) ||
                    (secondType instanceof NoType && !(firstType instanceof BoolType))) {
                UnsupportedOperandType exception = new UnsupportedOperandType(binaryExpression.getLine(), operator.name());
                binaryExpression.addError(exception);
                return new NoType();
            }
            else if(firstType instanceof NoType || secondType instanceof NoType) {
                return new NoType();
            }
            if((firstType instanceof BoolType) && (secondType instanceof BoolType)) {
                return new BoolType();
            }
        }
        if(operator == BinaryOperator.assign) {
            Expression left = binaryExpression.getFirstOperand();
            if(!(left instanceof Identifier || left instanceof StructAccess || left instanceof ListAccessByIndex)) {
                LeftSideNotLvalue exception = new LeftSideNotLvalue(binaryExpression.getLine());
                binaryExpression.addError(exception);
            }
            if(firstType instanceof NoType || secondType instanceof NoType) {
                return new NoType();
            }
            if(firstType instanceof IntType || firstType instanceof BoolType || firstType instanceof FptrType
                    || firstType instanceof ListType) {
                if (firstType.toString().equals(secondType.toString())) {
                    return firstType;
                }
            }
            if((firstType instanceof StructType && secondType instanceof StructType)) {
                if (((StructType) firstType).getStructName().getName().equals(((StructType) secondType).getStructName().getName())) {
                    return firstType;
                }
            }
            UnsupportedOperandType exception = new UnsupportedOperandType(binaryExpression.getLine(), operator.name());
            binaryExpression.addError(exception);
            return new NoType();
        }
        UnsupportedOperandType exception = new UnsupportedOperandType(binaryExpression.getLine(), operator.name());
        binaryExpression.addError(exception);
        return new NoType();
    }

    @Override
    public Type visit(UnaryExpression unaryExpression) {
        //Todo: done:)
        Type operandType = unaryExpression.getOperand().accept(this);
        UnaryOperator operator = unaryExpression.getOperator();
        if(operator == UnaryOperator.not) {
            if(operandType instanceof NoType)
                return new NoType();
            if(operandType instanceof BoolType)
                return operandType;
        }
        else if(operator == UnaryOperator.minus) {
            if(operandType instanceof NoType)
                return new NoType();
            if(operandType instanceof IntType)
                return operandType;
        }
        UnsupportedOperandType exception = new UnsupportedOperandType(unaryExpression.getLine(), operator.name());
        unaryExpression.addError(exception);
        return new NoType();
    }

    @Override
    public Type visit(FunctionCall funcCall) {
        //Todo
        return null;
    }

    @Override
    public Type visit(Identifier identifier) {
        //Todo
        return null;
    }

    @Override
    public Type visit(ListAccessByIndex listAccessByIndex) {
        //Todo
        return null;
    }

    @Override
    public Type visit(StructAccess structAccess) {
        //Todo
        return null;
    }

    @Override
    public Type visit(ListSize listSize) {
        //Todo
        return null;
    }

    @Override
    public Type visit(ListAppend listAppend) {
        //Todo
        return null;
    }

    @Override
    public Type visit(ExprInPar exprInPar) {
        //Todo
        return null;
    }

    @Override
    public Type visit(IntValue intValue) {
        //Todo: done:)
        return new IntType();
    }

    @Override
    public Type visit(BoolValue boolValue) {
        //Todo: done:)
        return new BoolType();
    }
}
