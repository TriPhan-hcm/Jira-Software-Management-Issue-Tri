package dxc.com.jira.soft.dashboard.controller;

import java.io.Console;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import dxc.com.jira.soft.dashboard.dao.issue.IssueData;
import dxc.com.jira.soft.dashboard.service.IssueService;

@Controller
public class IssueController {
	
	@Autowired
	private IssueService iisueService;
	
	//Widget 3: 
		@GetMapping("/issue")
		public String GetIssue(Model model) {
			model.addAttribute("dataIssue",iisueService.getIssueDefault());
			return "list-issue";
		}
		@GetMapping("/issue/search")
		public String searchPriorityIssue(@RequestParam(value="issueId")Long issueId,Model model,RedirectAttributes redirect) {
			if(StringUtils.isEmpty(issueId)) {
				return "redirect:/issue";
			}
			model.addAttribute("dataIssue", iisueService.findIssueId(issueId));
			
			return "list-issue";
		}
		
		@GetMapping("/issue/issue-type")
		public String searchIssueIncomplete(Model model) {
			List<IssueData> listComplete = iisueService.getIssueComplete();
			List<IssueData> listCategory = iisueService.getIssueCategory();
			
			List<IssueData> listInComplete = iisueService.getIssueInComplete();
			List<IssueData> listInCategory = iisueService.getIssueCategoryIn();
			
			List<IssueData> listNameCategory= iisueService.getIssueNameCategory();
			//show Complete Table
			model.addAttribute("dataComplete", listComplete);
			model.addAttribute("dataIssueCategory", listCategory);
			model.addAttribute("dataIssueNameCategory",listNameCategory);
			
			//show In-Complete Table
			
			model.addAttribute("dataIssueINCategory", listInComplete);		
			model.addAttribute("dataListInCategory",listInCategory);
			model.addAttribute("dataIssueNameCategory",listNameCategory);

			return "list-issue-type";
		}
}
