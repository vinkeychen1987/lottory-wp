package com.unison.lottery.weibo.dataservice.crawler.parse.repository;

/**
 * 国家
 * @author guixiangcui
 *
 */
public class g
{
  String a;
  String b;
  String c;

  public g(String paramString1, String paramString2, String paramString3)
  {
    this.a = paramString1;
    this.b = paramString2;
    this.c = paramString3;
  }

  public String a()
  {
    return this.a;
  }

  public void a(String paramString)
  {
    this.a = paramString;
  }

  public String b()
  {
    return this.b;
  }

  public void b(String paramString)
  {
    this.b = paramString;
  }

  public String c()
  {
    return this.c;
  }

  public void c(String paramString)
  {
    this.c = paramString;
  }

  public String toString()
  {
    return "Country [countryId=" + this.a + "continentId =" + this.c + ", countrynName=" + this.b + "]";
  }
}
