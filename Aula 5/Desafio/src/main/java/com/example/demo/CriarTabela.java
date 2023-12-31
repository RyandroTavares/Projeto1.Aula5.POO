package com.example.demo;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class CriarTabela extends JFrame {
	private Connection con;
	private Statement st;

	public CriarTabela() {
		String driver = "org.postgresql.Driver";
		String user = "postgres";
		String password = "123456";
		String url = "jdbc:postgresql://localhost:5555/atvd_anderson";

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			JOptionPane.showMessageDialog(this, "Banco conectado com sucesso!", "Mensagem",
					JOptionPane.WARNING_MESSAGE);

		} catch (SQLException eSQL) {

			eSQL.printStackTrace();
			JOptionPane.showMessageDialog(this, "Falha na conexão com o banco!\n" + "Mensagem: " + eSQL.getMessage(),
					"Erro", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		} catch (Exception e) {

			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Falha na conexão com o banco!\n" + "Mensagem: " + e.getMessage(),
					"Erro", JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		}

		String sentencaSQL = "CREATE TABLE produto (id integer PRIMARY KEY, nomeProduto VARCHAR(50));";

		try {
			st = con.createStatement();
			st.executeUpdate(sentencaSQL);
			JOptionPane.showMessageDialog(this, "Tabela criada com sucesso!", "Mensagem", JOptionPane.WARNING_MESSAGE);
		} catch (SQLException eSQL) {
			eSQL.printStackTrace();
			JOptionPane.showMessageDialog(this, "Não foi possível criar a tabela!\n" + "Mensagem: " + eSQL.getMessage(),
					"Erro", JOptionPane.ERROR_MESSAGE);
			System.exit(2);
		}

		try {
			st.close();
			con.close();
		} catch (Exception exception) {
			exception.printStackTrace();
			System.exit(3);
		}
		Container P = getContentPane();
		P.setLayout(new FlowLayout());
		JLabel mensagem = new JLabel("Você acabou de testar um exemplo usando CREATE TABLE!");
		P.add(mensagem);
	}

	public static void main(String args[]) {
		CriarTabela ex = new CriarTabela();
		ex.setDefaultCloseOperation(EXIT_ON_CLOSE);
		ex.setTitle("USANDO CREATE TABLE");
		ex.setVisible(true);
		ex.setSize(400, 300);
	}
}
