/*
 * [y] hybris Platform
 *
 * Copyright (c) 2018 SAP SE or an SAP affiliate company. All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with SAP.
 */
package de.hybris.training.controller;

import static de.hybris.training.constants.TrainingcommerceservicesConstants.PLATFORM_LOGO_CODE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import de.hybris.training.service.TrainingcommerceservicesService;


@Controller
public class TrainingcommerceservicesHelloController
{
	@Autowired
	private TrainingcommerceservicesService trainingcommerceservicesService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String printWelcome(final ModelMap model)
	{
		model.addAttribute("logoUrl", trainingcommerceservicesService.getHybrisLogoUrl(PLATFORM_LOGO_CODE));
		return "welcome";
	}
}
