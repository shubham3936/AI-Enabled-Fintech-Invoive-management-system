package com.highradius;

import java.sql.*;
import java.util.HashMap;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/**
 * Servlet implementation class AddData
 */
@WebServlet("/AddData")
public class AddData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddData() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			HashMap<Object, Object> Response = new HashMap<Object, Object>();
			String BusinessCode = request.getParameter("businessCode");
			String CustomerNumber = request.getParameter("CustomerNumber");
			String ClearDate = request.getParameter("clearDate");
			String BusinessYear = request.getParameter("businessYear");
			String DocID = request.getParameter("docID");
			String PostingDate = request.getParameter("postingDate");
			String DocumentcreateDate = request.getParameter("documentcreateDate");
			String DueinDate = request.getParameter("dueinDate");
			String InvoiceCurrency = request.getParameter("invoiceCurrency");
			String DocumentType = request.getParameter("documentType");
			String PostingID = request.getParameter("postingID");
			String TotalopenAmount = request.getParameter("totalopenAmount");
			String BaselinecreateDate = request.getParameter("baselinecreateDate");
			String CustpaymentTerms = request.getParameter("custpaymentTerms");
			String InvoiceID = request.getParameter("invoiceID");
			Connection con = JDBCConnection.createConnect();
			String query = "INSERT INTO winter_internship (business_code, cust_number, clear_date, buisness_year, "
					+ "doc_id,posting_date,document_create_date,"
					+ "due_in_date,invoice_currency,document_type,posting_id,total_open_amount,"
					+ "total_open_amount,baseline_create_date,cust_payment_terms,invoice_id,) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement st = con.prepareStatement(query);
			st.setString(1, BusinessCode);
			st.setString(2, CustomerNumber);
			st.setString(3, ClearDate);
			st.setString(4, BusinessYear);
			st.setString(5,  DocID);
			st.setString(6, PostingDate);
			st.setString(7, DocumentcreateDate);
			st.setString(8, DueinDate);
			st.setString(9, InvoiceCurrency);
			st.setString(10, DocumentType);
			st.setString(11, PostingID);
			st.setString(12, TotalopenAmount);
			st.setString(13, BaselinecreateDate);
			st.setString(14, CustpaymentTerms);
			st.setString(15, InvoiceID);
			if(st.executeUpdate() >0) {;
				Response.put("insert", true);
			}else {
				Response.put("insert", false);
			}
			Gson gson = new Gson();
			String result = gson.toJson(Response);
			response.setHeader("Access-Control-Allow-Origin", "*");
			response.getWriter().append(result);
			con.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
