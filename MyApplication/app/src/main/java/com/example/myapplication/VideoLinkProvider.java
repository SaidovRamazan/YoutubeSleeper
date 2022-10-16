package com.example.myapplication;

import android.annotation.SuppressLint;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.io.*;

public class VideoLinkProvider {

    @SuppressLint("SetJavaScriptEnabled")
    String getVideoLink(String youtubeUrl) throws IOException {

        String videoLink = "";
        WebClient webClient = new WebClient();
        webClient.getOptions().setJavaScriptEnabled(true); // enable javascript
        webClient.getOptions().setThrowExceptionOnScriptError(false); //even if there is error in js continue
        webClient.waitForBackgroundJavaScript(15000); // important! wait until javascript finishes rendering

        String saveFromUrl = "http://ru.savefrom.net/#url=";
        HtmlPage page = webClient.getPage(saveFromUrl + youtubeUrl);
        HtmlElement linkElement = page.getFirstByXPath("//a[contains(@class, 'download-icon')");

        videoLink = linkElement.getAttribute("href");

        System.out.println("Ссылка на видео: " + videoLink);

        webClient.close();

        return videoLink;
    }

}
