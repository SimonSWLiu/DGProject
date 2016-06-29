package com.onemenu.server.controllerImpl;

import org.apache.commons.io.FilenameUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ArrayNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.onemenu.server.controller.CommonController;
import com.onemenu.server.service.AccountService;
import com.onemenu.server.util.ImageUtils;

/**
 * 
 * @author lin
 *
 */
@Controller
@RequestMapping("/commonController")
public class CommonControllerImpl implements CommonController {

    private AccountService mAccountService;

    /**
     * 框架页面
     */
    @RequestMapping("/showMainPage")
    public ModelAndView showMainPage() {
        return new ModelAndView("restaurant/Main");
    }

    /**
     * 服务器信息页面
     */
    @RequestMapping("/showMainContentPage")
    public ModelAndView showMainContentPage() {
        ModelAndView model = new ModelAndView("restaurant/MainContent");

        return model;
    }

    @RequestMapping("/showPendingApprovalMsgPage")
    public ModelAndView showPendingApprovalMsgPage() {

        ModelAndView model = new ModelAndView("restaurant/PendingApproval");

        return model;
    }

    // jquery-validation call
    @RequestMapping(value = "/validate", method = RequestMethod.GET)
    @ResponseBody
    public Object validateField(String fieldId, String fieldValue, String _) {

        ObjectMapper mapper = new ObjectMapper();
        ArrayNode arrayNode = mapper.createArrayNode();

        boolean result;

        if (fieldId.equals("email")) {
            arrayNode.add(fieldId);
            result = mAccountService.validateEmail(fieldValue);
            arrayNode.add(result);
        }

        return arrayNode;
    }

    @RequestMapping(value = "/generateBase64String", method = RequestMethod.POST)
    @ResponseBody
    public String generateBase64String(MultipartFile file) throws Exception {

        if (file != null) {
            String extension = FilenameUtils.getExtension(file.getOriginalFilename());
            String base64Str =
                    ImageUtils.getBase64FromInputStream(file.getInputStream(), extension,
                            ImageUtils.IMAGE_WIDTH_540);

            return base64Str;
        }

        return "";
    }

    @Autowired
    public void setmAccountService(AccountService mAccountService) {
        this.mAccountService = mAccountService;
    }

}
