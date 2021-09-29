package com.crazysusanin.planning.service;

import com.crazysusanin.planning.model.AviaTicketInfo;
import com.crazysusanin.planning.model.User;
import com.crazysusanin.planning.utils.AppConstants;
import com.crazysusanin.planning.utils.EmailUtils;
import com.crazysusanin.planning.utils.GeneratePDF;
import com.itextpdf.layout.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;


@Service
public class ThreadForPro {

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @Autowired
    AviaTicketInfoService aviaTicketInfoService;

    @Autowired
    GeneratePDF generatePDF;



    @Async("taskExecutor")
    public void sendMSG () throws InterruptedException{

        System.out.println("Execute method with configured executor - "
                + Thread.currentThread().getName());
        boolean flag = true;

        while (flag) {
            //get users with role "pro"

            List<User> usersWithRolePRO = userService.findAllByRole(roleService.findByType("pro"));
            if (usersWithRolePRO != null) {
                List<List<AviaTicketInfo>> checkList = new ArrayList<>();
                //get AviaTicketInfo
                for (User user : usersWithRolePRO) {
                    checkList.add(aviaTicketInfoService.getAviaTicketInfoByUser(user));
                }

                    for (List<AviaTicketInfo> listAviaTicketInfo : checkList) {
                        for (AviaTicketInfo aviaTicketInfo : listAviaTicketInfo) {
                            int id = aviaTicketInfo.getId();
                            User user = aviaTicketInfo.getUser();
                            System.out.println("thread working....");
                            //sending msg on email
                            try {
                                sleep(3000);
                                Document doc = generatePDF.generatePDFByAviaTicketInfo(id);

                                EmailUtils.sendMessageWithPDF(user.getEmail(), AppConstants.EMAIL_ID,
                                        "CRAZY USERS: You were looking for...","D:\\generate_pdf\\\\ticketInfoForPro"+id+".pdf");

                            } catch (FileNotFoundException | InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }

            } else {
                flag = false;
                System.out.println("stop doing");
            }
        }

    }


}
