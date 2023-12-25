package org.nagura.cmajava;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;

public class ASTCreation {
	
	public static CompilationUnit createAST(String filePath) {
		try {
			String fileContent = readFileContent(filePath);
			calculateCodeMetrics(filePath);
			CompilationUnit unit = parseAST(fileContent);
			// Increment the file count after successful parsing
			CodeMetrics.totalFiles++;
			return unit;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private static String readFileContent(String filePath) throws IOException {
		StringBuilder sb = new StringBuilder();
		try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)))) {
			String line;
			while ((line = br.readLine()) != null) {
				sb.append(line).append("\n");
			}
		}
		return sb.toString();
	}

	private static void calculateCodeMetrics(String filePath) throws IOException {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)))) {
			String line;
			boolean inMultiLineComment = false;
			while ((line = br.readLine()) != null) {
				if (line.trim().isEmpty()) {
					CodeMetrics.totalBlankLines++;
				} else if (line.trim().startsWith("/*") && line.trim().endsWith("*/")) {
					CodeMetrics.totalCommentLines++;
				} else if (line.trim().startsWith("/*")) {
					inMultiLineComment = true;
					CodeMetrics.totalCommentLines++;
				} else if (line.trim().endsWith("*/")) {
					inMultiLineComment = false;
					CodeMetrics.totalCommentLines++;
				} else if (inMultiLineComment) {
					CodeMetrics.totalCommentLines++;
				} else if (line.trim().startsWith("//")) {
					CodeMetrics.totalCommentLines++;
				} else {
					CodeMetrics.totalCodeLines++;
				}
			}
		}
	}

	private static CompilationUnit parseAST(String fileContent) {
		@SuppressWarnings("deprecation")
		ASTParser parser = ASTParser.newParser(AST.JLS8);
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
		parser.setSource(fileContent.toCharArray());
		return (CompilationUnit) parser.createAST(new NullProgressMonitor());
	}	
}
