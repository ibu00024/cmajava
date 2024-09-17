package org.nagura.cmajava;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.Javadoc;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.MethodDeclaration;

public class BlockVisitor extends ASTVisitor {

    @Override
    public boolean visit(TypeDeclaration node) {
        // Only class and local class declarations count (excluding anonymous classes and interfaces)
        if (!node.isInterface()) {
            CodeMetrics.totalClasses++;
        }
        return super.visit(node);
    }

    @Override
    public boolean visit(MethodDeclaration node) {
        CodeMetrics.totalMethodDeclarations++;

        // Parse whether javadoc exists
        Javadoc javadoc = node.getJavadoc();
        if (javadoc != null) {
            CodeMetrics.totalJavadocsOfMethod++;
        }

        return super.visit(node);
    }
    
    @Override
    public boolean visit(MethodInvocation node) {
        CodeMetrics.totalMethodInvocations++;
        return super.visit(node);
    }

    @Override
    public boolean visit(Javadoc node) {
        CodeMetrics.totalJavadocs++;
        return super.visit(node);
    }

}
