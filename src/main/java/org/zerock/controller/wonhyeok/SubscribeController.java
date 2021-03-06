package org.zerock.controller.wonhyeok;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zerock.domain.MemberVO;
import org.zerock.domain.SubsFinalInfoVO;
import org.zerock.domain.SubscribeDate;
import org.zerock.domain.SubscribeViewVO;
import org.zerock.domain.SubscriberInfoVO;
import org.zerock.service.SubscribeInfoService;
import org.zerock.service.WonhyeokRestService;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/subscribe/*")
@AllArgsConstructor
@Log4j
public class SubscribeController {

	@Setter(onMethod_ = @Autowired)
	private WonhyeokRestService service;
	private SubscribeInfoService subinfoservice;


	@RequestMapping("/subsregister")
	@PreAuthorize("hasRole('ROLE_USER') || hasRole('ROLE_ADMIN')")
	public void main() {

	}

	@PostMapping("/getNearStoreInfo")
	@PreAuthorize("hasRole('ROLE_USER') || hasRole('ROLE_ADMIN')")
	@ResponseBody
	public List<SubscribeViewVO> getNearStoreInfo(MemberVO vo) {
		List<SubscribeViewVO> subsvo =service.getNearStireInfo(vo);


		return subsvo;
	}

	@RequestMapping("/sms")
	public void sms() {

	}

	@PostMapping(value = "/saveSubsInfo", produces = { "application/json; charset=utf-8" })
	@PreAuthorize("hasRole('ROLE_USER') || hasRole('ROLE_ADMIN')")
	@ResponseBody
	public void subscriberInfo(@RequestBody SubscriberInfoVO info) {
		service.SaveSubscriberInfo(info);
	}

	@GetMapping("/getStoreList")
	@PreAuthorize("hasRole('ROLE_USER') || hasRole('ROLE_ADMIN')")
	@ResponseBody
	public List<SubscribeViewVO> getStoreList() {
		List<SubscribeViewVO> list = service.getStoreList();

		return list;

	}

	@PostMapping("/getStoreListBySearch")
	@PreAuthorize("hasRole('ROLE_USER') || hasRole('ROLE_ADMIN')")
	@ResponseBody
	public List<SubscribeViewVO> getStoreListBySearch(SubscribeViewVO vo) {
		List<SubscribeViewVO> list = service.getStoreListBySearch(vo);
		return list;
	}

	// ?????? ?????? ?????? ??????
	@PostMapping("/finalinfo")
	public void getFinalinfo() {

	}


	// ?????? ?????????
	@GetMapping("/finalinfo")
	public String calendar(Model model, 
			HttpServletRequest request, 
			SubscribeDate dateData) {

		
//		List<SubscribeDate> dateList = subinfoservice.getDateList(dateData);
//		Map<String, Integer> today_info =  dateData.today_info(dateData);
		
		// ?????? ?????????
			Calendar cal = Calendar.getInstance();

			SubscribeDate calendarData;

			// ?????? ??????
			if(dateData.getDate().equals("") && dateData.getMonth().equals("")){

				dateData = new SubscribeDate(String.valueOf(cal.get(Calendar.YEAR)),
						String.valueOf(cal.get(Calendar.MONTH)),
						String.valueOf(cal.get(Calendar.DATE)),
						null);
			}

			// ?????? ?????? end

			Map<String, Integer> today_info =  dateData.today_info(dateData);

			List<SubscribeDate> dateList = new ArrayList<SubscribeDate>();

			// ???????????? ?????? ????????? ???????????? ????????? ?????? ??????.
			// ?????? ?????? ??????????????? ???????????? ?????? ????????? ??????
			for(int i = 1; i < today_info.get("start"); i++){

				calendarData = new SubscribeDate(null, null, null, null);

				dateList.add(calendarData);
			}

			// ?????? ??????
			for (int i = today_info.get("startDay"); i <= today_info.get("endDay"); i++) {

				if (i == today_info.get("today")) {

					calendarData = new SubscribeDate(String.valueOf(dateData.getYear()), 
							String.valueOf(dateData.getMonth()), 
							String.valueOf(i), 
							"today");
				} else {
					calendarData = new SubscribeDate(String.valueOf(dateData.getYear()), 
							String.valueOf(dateData.getMonth()), 
							String.valueOf(i), 
							"normal_date");
				}
				dateList.add(calendarData);
			}

			// ?????? ??? ??? ??? ???????????? ?????? (index)
			int index = 7 - dateList.size() % 7;

			if(dateList.size() % 7 != 0){

				for (int i = 0; i < index; i++) {

					calendarData = new SubscribeDate(null, null, null, null);

					dateList.add(calendarData);
				}
			}

		List<String> realSubDateList = subinfoservice.getSubDateList(dateData);

		
		/*
		 * log.info(userid); SubsFinalInfoVO vo = service.ABCD(userid);
		 */

		model.addAttribute("dateList", dateList);		//?????? ????????? ??????
		model.addAttribute("today_info", today_info);
		model.addAttribute("subDateList", realSubDateList);
		/* model.addAttribute("subsInfo", vo); */

		
		
		// ?????? ????????? ??????

		return "/subscribe/finalinfo";
	}
	
	@PostMapping("ABCD")
	@ResponseBody
	public SubsFinalInfoVO ABCD(String userid) {
		SubsFinalInfoVO vo = service.ABCD(userid);
		return vo;
	}
	
	

}
