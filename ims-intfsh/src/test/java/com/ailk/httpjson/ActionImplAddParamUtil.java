package com.ailk.httpjson;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * @Description: 给CN_SHMgntActionImpl.java增加标注，以便支持http+json
 * @author wangjt
 * @Date 2012-4-17
 */
public class ActionImplAddParamUtil
{
    public static void main(String[] args) throws Exception
    {
        String path = System.getProperty("user.dir")
                + "/src/main/java/com/ailk/openbilling/action/imscnsh/CN_SHMgntActionImpl.java";

        Scanner scan = new Scanner(new File(path));
        scan.useDelimiter("\n");
        StringBuilder sb = new StringBuilder();
        while (scan.hasNext())
        {
            String s = scan.next();
            if (!s.trim().startsWith("public Do_"))
            {
                sb.append(s);
                sb.append("\n");
                continue;
            }
            s = s.replaceAll("@[^\\s]+\\s*", "");
            String[] strings = s.split("[()]");
            sb.append(strings[0]);
            sb.append("(");

            String sub = strings[1];// SOperInfo sOper,Long acct_id
            String[] ps = sub.split(",");
            for (int i = 0; i < ps.length; i++)
            {
                String p = ps[i];// SOperInfo sOper
                String typeStr = "";
                if (p.startsWith("Long ") || p.startsWith("String ") || p.startsWith("Integer ") || p.startsWith("Short ")
                        || p.startsWith("Boolean "))
                {
                    typeStr = ",type=ParamType.JSON";
                }
                String ss = "@Param(value=\"" + p.split("\\s")[1] + "\"" + typeStr + ") " + p;
                if (i != 0)
                {
                    sb.append(",");
                }
                sb.append(ss);
            }
            sb.append(")");
            sb.append(strings[2]);
            sb.append("\n");
        }

        PrintWriter out = new PrintWriter(path);
        out.print(sb.toString());
        out.close();
    }
}
