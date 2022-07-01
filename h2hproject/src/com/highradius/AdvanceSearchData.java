package com.highradius;

import java.sql.*;
import java.util.ArrayList;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.highradius.JDBCConnection;
import com.highradius.Response;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Servlet implementation class SearchData
 */
@WebServlet("/SearchData")
public class AdvanceSearchData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdvanceSearchData() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		try {
			Connection con = JDBCConnection.createConnect();
			String custnumber = request.getParameter("custnumber");
			String docid = request.getParameter("docid");
			String invoiceid = request.getParameter("invoiceid");
			String buisnessyear = request.getParameter("buisnessyear");
			Statement st = con.createStatement();
			String sql_statement = "SELECT * FROM winter_internship WHERE doc_id=" + 
			"'"+docid+"'"+"AND cust_number="+"'"+custnumber+"'"+"AND invoice_id="
					+"'"+invoiceid+"'"+"AND buisness_year="+"'"+buisnessyear+"'";
			ResultSet rs = st.executeQuery(sql_statement);
			ArrayList<Response> data = new ArrayList<>();
			while(rs.next()) {
				Response newdata = new Response();
				newdata.setID(rs.getInt("sl_no"));
				newdata.setBusinessCode(rs.getString("business_code"));
				newdata.setCustomerNumber(rs.getInt("cust_number"));
				newdata.setClearDate(rs.getString("clear_date"));
				newdata.setBusinessYear(rs.getInt("buisness_year"));
				newdata.setDocID(rs.getString("doc_id"));
				newdata.setPostingDate(rs.getString("posting_date"));
				newdata.setDocumentcreateDate(rs.getString("document_create_date"));
				newdata.setDocumentcreateDate1(rs.getString("document_create_date1"));
				newdata.setDueinDate(rs.getString("due_in_date"));
				newdata.setInvoiceCurrency(rs.getString("invoice_currency"));
				newdata.setDocumentType(rs.getString("document_type"));
				newdata.setPostingID(rs.getInt("posting_id"));
				newdata.setTotalopenAmount(rs.getDouble("total_open_amount"));
				newdata.setBaselinecreateDate(rs.getString("baseline_create_date"));
				newdata.setCustpaymentTerms(rs.getString("cust_payment_terms"));
				newdata.setInvoiceID(rs.getInt("invoice_id"));
				newdata.setIsOpen(rs.getShort("isOpen"));

				data.add(newdata);
			}
			Gson gson = new GsonBuilder().serializeNulls().create();
			String invoices  = gson.toJson(data);
			response.setContentType("application/json");
			try {
				response.getWriter().write(invoices); 
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
			rs.close();
			st.close();
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
		doGet(request, response);
	}

}
