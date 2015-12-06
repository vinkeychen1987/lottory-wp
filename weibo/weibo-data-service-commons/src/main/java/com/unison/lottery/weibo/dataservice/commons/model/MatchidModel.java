package com.unison.lottery.weibo.dataservice.commons.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
@XmlRootElement(name="match")
public class MatchidModel {

	
		
		@XmlElement(name="i")
		public List<MatchidContentModel> contentModel;

		
		@Override
		public String toString() {
			return ReflectionToStringBuilder.toString(this,
					ToStringStyle.MULTI_LINE_STYLE);
		}


	
		
		
	}