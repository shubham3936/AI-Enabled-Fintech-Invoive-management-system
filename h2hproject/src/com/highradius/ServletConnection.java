package com.highradius;

import java.sql.*;
import java.io.PrintWriter;
import java.util.ArrayList;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.highradius.Response;
import com.google.gson.Gson;


@WebServlet("/ServletConnection")
public class ServletConnection extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String QUERY = "SELECT * FROM winter_internship";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletConnection() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = JDBCConnection.createConnect();
			Statement st = con.createStatement();
			ResultSet resultSet = st.executeQuery(QUERY);
			ArrayList<Response> data = new ArrayList<>();
				
				while (resultSet.next()) {
					Response newdata = new Response();
					newdata.setID(resultSet.getInt("sl_no"));
					newdata.setBusinessCode(resultSet.getString("business_code"));
					newdata.setCustomerNumber(resultSet.getInt("cust_number"));
					newdata.setClearDate(resultSet.getString("clear_date"));
					newdata.setBusinessYear(resultSet.getInt("buisness_year"));
					newdata.setDocID(resultSet.getString("doc_id"));
					newdata.setPostingDate(resultSet.getString("posting_date"));
					newdata.setDocumentcreateDate(resultSet.getString("document_create_date"));
					newdata.setDocumentcreateDate1(resultSet.getString("document_create_date1"));
					newdata.setDueinDate(resultSet.getString("due_in_date"));
					newdata.setInvoiceCurrency(resultSet.getString("invoice_currency"));
					newdata.setDocumentType(resultSet.getString("document_type"));
					newdata.setPostingID(resultSet.getInt("posting_id"));
					newdata.setTotalopenAmount(resultSet.getDouble("total_open_amount"));
					newdata.setBaselinecreateDate(resultSet.getString("baseline_create_date"));
					newdata.setCustpaymentTerms(resultSet.getString("cust_payment_terms"));
					newdata.setInvoiceID(resultSet.getInt("invoice_id"));
					newdata.setIsOpen(resultSet.getShort("isOpen"));
					data.add(newdata);
				}
				
				Gson gson = new Gson();
				String result = gson.toJson(data);
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				PrintWriter writer = response.getWriter();
				writer.print(result);
				writer.flush();
				writer.close();
		} catch (SQLException | ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
