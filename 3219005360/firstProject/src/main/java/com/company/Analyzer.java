package com.company;

import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 * ÖÐÎÄ·Ö´Ê
 * @return
 */
public class Analyzer {

    public static List<String> stringParticiple(String text) throws IOException {
        StringReader stringReader=new StringReader(text);
        IKSegmenter ikSegmenter=new IKSegmenter(stringReader,true);
        Lexeme lex;
        List<String> list=new ArrayList<String>();
        while((lex=ikSegmenter.next())!=null){
            list.add(lex.getLexemeText());
        }
        return list;
    }
}
