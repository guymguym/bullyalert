package me.bullyalert.servlets;

import com.centirion.commons.RandomGenerator;
import com.centirion.commons.strings.StringUtils;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import me.bullyalert.AppEmail;
import me.bullyalert.AppSMS;
import me.bullyalert.dal.dao.DAO;
import me.bullyalert.dal.pojo.Subscriber;

public class SubscribeServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            String emailOrPhone = request.getParameter("email_or_phone");
            String twitterMonitor = request.getParameter("twitter_account");
            if (twitterMonitor.startsWith("@")) {
                twitterMonitor = twitterMonitor.substring(1);
            }
            Subscriber subscriber = new Subscriber();
            subscriber.setId(RandomGenerator.generateString(16));
            subscriber.setTwitterMonitor(twitterMonitor);
            if (!StringUtils.isNullOrEmpty(emailOrPhone)) {
                if (StringUtils.isPhoneNumber(emailOrPhone)) {
                    subscriber.setPhone(emailOrPhone);
                    new AppSMS().sendSubscriptionWelcomeSMS(subscriber);
                } else {
                    subscriber.setEmail(emailOrPhone);
                    new AppEmail().sendSubscriptionWelcomeEmail(emailOrPhone);
                }
                DAO.getInstance().saveSubscriber(subscriber);
                out.println("OK");
            }
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
