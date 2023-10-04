package afrock.dance.map.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/page")
class PageController {

    @GetMapping(path = ["", "/"])
    fun forwardToIndex(): String {
        return "forward:/page/index.html"
    }
}
