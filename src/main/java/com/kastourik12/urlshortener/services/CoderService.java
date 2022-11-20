/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package com.kastourik12.urlshortener.services;


/**
 *
 * @author ok
 */
public interface CoderService {
    public String codeIdToShortUrl(Long id);
    public Long decodeShortUrlToId(String shortUrl);

}
