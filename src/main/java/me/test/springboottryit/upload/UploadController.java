package me.test.springboottryit.upload;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author paranoidq
 * @since 1.0.0
 */
@Controller
@Slf4j
public class UploadController {
    private String UPLOAD_FOLDER = "";

    @PostMapping("/upload")
    public String singleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        if (file.isEmpty()) {
            /*
             * addAttribute vs addFlashAttribute
             * 前者是将属性放到request的参数中，进行redirect，因此受到http报文长度限制
             * 后者则是将属性放到一个临时的FlashMap中，这个FlashMap是存储在session中的，因此不受http报文长度的限制，并且在重定向之后被立即移除
             */
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:/uploadStatus";    // just for test
        }
        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOAD_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);

            redirectAttributes.addFlashAttribute("message", "You successfully uploaded '" + file.getOriginalFilename() + "'");
        } catch (IOException e) {
            log.error("", e);
        }
        return "redirect:/uploadStatus";
    }

}
