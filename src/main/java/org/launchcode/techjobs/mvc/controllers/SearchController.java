package org.launchcode.techjobs.mvc.controllers;

import org.launchcode.techjobs.mvc.models.Job;
import org.launchcode.techjobs.mvc.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

import static org.launchcode.techjobs.mvc.controllers.ListController.columnChoices;


/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @GetMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", columnChoices);
        return "search";
    }

    // TODO #3 - Create a handler to process a search request and render the updated search view.

    /*1. configure the correct mapping type and mapping route, refer to the form tag in the search.html template.
         (Use @GetMapping or @PostMapping rather than @RequestMapping.)
      2. The displaySearchResults method should take in a Model parameter.
      3. specifying the type of search and the search term.
      4. you need to name them appropriately, based on the corresponding form field names defined in search.html.
      5. send the search information to findByColumnAndValue. In either case, store the results in a jobs ArrayList.
      6. Pass jobs into the search.html view via the model parameter.
      7. Pass ListController.columnChoices into the view, as the existing search handler does
      */

    @PostMapping(value = "results")
    public String displaySearchResults(Model model, @RequestParam String searchType, @RequestParam String searchTerm) {
        ArrayList<Job> jobs;
        jobs = JobData.findByColumnAndValue(searchType, searchTerm);

        model.addAttribute("type", searchType);
        model.addAttribute("columns", columnChoices);
        model.addAttribute("title", "Search Condition: " + columnChoices.get(searchType) + " Search Term: " + searchTerm);
        model.addAttribute("jobs", jobs);

        return "search";


    }

}
