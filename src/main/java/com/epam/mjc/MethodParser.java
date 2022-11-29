package com.epam.mjc;

import java.util.ArrayList;
import java.util.List;

public class MethodParser {

    /**
     * Parses string that represents a method signature and stores all it's members into a {@link MethodSignature} object.
     * signatureString is a java-like method signature with following parts:
     *      1. access modifier - optional, followed by space: ' '
     *      2. return type - followed by space: ' '
     *      3. method name
     *      4. arguments - surrounded with braces: '()' and separated by commas: ','
     * Each argument consists of argument type and argument name, separated by space: ' '.
     * Examples:
     *      accessModifier returnType methodName(argumentType1 argumentName1, argumentType2 argumentName2)
     *      private void log(String value)
     *      Vector3 distort(int x, int y, int z, float magnitude)
     *      public DateTime getCurrentDateTime()
     *
     * @param signatureString source string to parse
     * @return {@link MethodSignature} object filled with parsed values from source string
     */
    public MethodSignature parseFunction(String signatureString) {

        int openBracePosition = signatureString.indexOf("(");
        int closeBracePosition = signatureString.lastIndexOf(")");
        String methodDeclaration = signatureString.substring(0, openBracePosition);
        String[] methodDeclarationWords = methodDeclaration.split(" ");
        MethodSignature methodSignature;
        List<MethodSignature.Argument> arguments = new ArrayList<>();
        String methodArguments = signatureString.substring(openBracePosition + 1, closeBracePosition);
        String[] argumentList = methodArguments.split(", ");

        for (int i = 0; i < argumentList.length; i++) {
            if (methodArguments.length() == 0) {
                break;
            }
            String[] argumentWords = argumentList[i].split(" ");
            arguments.add(new MethodSignature.Argument(argumentWords[0], argumentWords[1]));
        }

        if (methodDeclarationWords.length > 2) {
            if (arguments.size() > 0) {
                methodSignature = new MethodSignature(methodDeclarationWords[2], arguments);
            }
            else {
                methodSignature = new MethodSignature(methodDeclarationWords[2]);
            }
            methodSignature.setAccessModifier(methodDeclarationWords[0]);
            methodSignature.setReturnType(methodDeclarationWords[1]);
        }
        else {
            if (arguments.size() > 0) {
                methodSignature = new MethodSignature(methodDeclarationWords[1], arguments);
            }
            else {
                methodSignature = new MethodSignature(methodDeclarationWords[1]);
            }
            methodSignature.setReturnType(methodDeclarationWords[0]);
        }

        return  methodSignature;
    }
}
