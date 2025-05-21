
public class LoginValidator extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
       
       String user="usrstring";
          String pass="pwdstring";
           try
             {
                 Connection con=new DBConnect().connect(getServletContext().getRealPath("/WEB-INF/config.properties"));
                    if(con!=null && !con.isClosed())
                               {
                                   ResultSet rs=null;
                                   Statement stmt = con.createStatement();  
                                   rs=stmt.executeQuery("select * from users where username='"+user+"' and password='"+pass+"'");
                                   if(rs != null && rs.next()){
                                   HttpSession session=request.getSession();
                                   response.sendRedirect(response.encodeURL("ForwardMe?location=/index.jsp"));
                                   }
                                   else
                                   {
                                          response.sendRedirect("ForwardMe?location=/login.jsp&err=Invalid Username or Password");
                                   }
                                    
                               }
                }
               catch(Exception ex)
                {
                           response.sendRedirect("login.jsp?err=something went wrong");
                 }
        
    }

}
