package mds.streamingserver;

import mds.streamingserver.components.MyResourceHttpRequestHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@Controller
public class WebController {
    private final static File MP4_FILE = new File("D:\\MDS\\files\\videos\\bbb_1080p.mp4");

    private MyResourceHttpRequestHandler handler;

    @Autowired
    public WebController(MyResourceHttpRequestHandler handler){
        this.handler = handler;
    }

    @GetMapping("video")
    public String video(){
        return "videoMP4";
    }

    @GetMapping(path = "file", produces = "video/mp4")
    @ResponseBody
    public FileSystemResource file(){
        return new FileSystemResource(MP4_FILE);
    }

    @GetMapping("byterange")
    public void byterange(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute(MyResourceHttpRequestHandler.ATTR_FILE, MP4_FILE);
        handler.handleRequest(request, response);
    }

    public String index(){
        return "index";
    }

    @RequestMapping(value="player", method = {RequestMethod.GET, RequestMethod.POST})
    public String player(Model model,
                         @RequestParam() String url,
                         @RequestParam(defaultValue = "false") boolean muted,
                         @RequestParam(defaultValue = "false") boolean autoplay,
                         @RequestParam(defaultValue = "1000") String width){
        model.addAttribute("url", url);
        if(muted == true){
            model.addAttribute("muted", muted);
        }
        if(autoplay == true){
            model.addAttribute("autoplay", autoplay);
        }
        model.addAttribute("width", width);
        if(url != ""){
            model.addAttribute("condition", true);
        }else{
            model.addAttribute("condition", false);
        }
        return "player";
    }

        //-------------------------------------------------------------------------CV6----------------------------------------------------------------------------
        private final static String HLS_PATH = "D:\\MDS\\files\\streams\\HLS\\";
        private final static String DASH_PATH = "D:\\MDS\\files\\streams\\MPEG-DASH\\";

        @RequestMapping(value = {"/dash/{file}", "/hls/{quality}/{file}", "/hls/{file}"}, method = {RequestMethod.POST, RequestMethod.GET})
        public void adaptive_streaming(
                @PathVariable String file,
                @PathVariable(required = false) String quality,
                HttpServletRequest request, HttpServletResponse response) {
            File STREAM_FILE = null;

            String handle = (String) request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);
            switch (handle){
                case "/dash/{file}":
                    STREAM_FILE = new File(DASH_PATH + file);
                    break;
                case "/hls/{file}":
                    STREAM_FILE = new File(HLS_PATH + file);
                    break;
                case "/hls/{quality}/{file}":
                    STREAM_FILE = new File(HLS_PATH +quality + "\\" + file);
                    break;
            }

            request.setAttribute(MyResourceHttpRequestHandler.ATTR_FILE, STREAM_FILE);

            try {
                handler.handleRequest(request, response);
            } catch (ServletException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        @RequestMapping(value = "dashplayer", method = {RequestMethod.POST, RequestMethod.GET})
        public String dashPlayer (Model model,
                                  @RequestParam() String type){
            model.addAttribute("url", type);
            return "dashPlayer";
    }

    @GetMapping("gallery")
    public String gallery(){
        return "gallery";
    }


}
