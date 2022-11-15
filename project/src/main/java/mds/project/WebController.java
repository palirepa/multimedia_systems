package mds.project;

import mds.project.components.ProjectResourceComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

import static mds.project.FilePaths.DASH_DIRECTORY;
import static mds.project.FilePaths.VIDEO_FROM_URL;

@Controller
public class WebController {

    private final ProjectResourceComponent handler;

    @Autowired
    public WebController(ProjectResourceComponent handler) {
        this.handler = handler;
    }

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/video", method = {RequestMethod.POST})
    public String video(@RequestParam(defaultValue = VIDEO_FROM_URL) String url,
                        @RequestParam(defaultValue = "1000") String width,
                        Model model) {

        if (!StringUtils.isEmpty(url)) {
            model.addAttribute("url", url);
            model.addAttribute("width", width);

        } else {
            model.addAttribute("error", "Nebyla zadána žádná adresa videa!");
            model.addAttribute("width", "1000");

        }

        return "video";
    }

    @RequestMapping(value = {"/dash/{stream}/{file}"}, method = RequestMethod.GET)
    public void streaming(
            @PathVariable("file") String file,
            @PathVariable(value = "stream", required = false) String stream,
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        File STREAM_FILE = new File(DASH_DIRECTORY + stream + "\\" + file);

        request.setAttribute(ProjectResourceComponent.ATTR_FILE, STREAM_FILE);
        handler.handleRequest(request, response);
    }

    @GetMapping("/player/{stream}")
    public String player(Model model,
                         @PathVariable("stream") String stream) {
        model.addAttribute("stream", stream);
        return "player";
    }

    @GetMapping("/videocollection")
    public String videoColllection(Model model) throws IOException {
        VideoLibrary library = new VideoLibrary();
        model.addAttribute("library", library);
        return "videocollection";
    }

}
