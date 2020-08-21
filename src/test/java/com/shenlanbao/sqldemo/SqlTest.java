package com.shenlanbao.sqldemo;

import java.util.*;

public class SqlTest {

    public static void main(String[] args) {
        String sql = "select c.phone, o.`recycle_time` , o.id, u.name, cg.name \n" +
                "  from `order` o\n" +
                "  left join `customer` c on c.id= o.`customer_id`\n" +
                "\tleft join \n" +
                "(select oe.*\n" +
                "  from `order` o\n" +
                "  left join `customer` c on c.id= o.`customer_id`\n" +
                "  left join order_event oe on oe.order_id= o.id\n" +
                " where o.type= 'family_insuance'\n" +
                "   and c.phone= '#####'\n" +
                "   and oe.`attribute` = 'consultant_id'\n" +
                "   and oe.created_at< o.recycle_time\n" +
                " order by oe.id desc\n" +
                " limit 1) cons on cons.order_id = o.id \n" +
                "left join \n" +
                "(select oe.*\n" +
                "  from `order` o\n" +
                "  left join `customer` c on c.id= o.`customer_id`\n" +
                "  left join order_event oe on oe.order_id= o.id\n" +
                " where o.type= 'family_insuance'\n" +
                "   and c.phone= '#####'\n" +
                "   and oe.`attribute` = 'consultant_group_id'\n" +
                "   and oe.created_at< o.recycle_time\n" +
                " order by oe.id desc\n" +
                " limit 1) g on g.order_id = o.id \n" +
                "\n" +
                "left join `consultant` co on co.id = cons.`current`\n" +
                "left join `user` u on u.id = co.`user_id` \n" +
                "left join `consultant_group` cg on cg.id = g.`current` \n" +
                " where o.type= 'family_insuance'\n" +
                "   and c.phone= '#####'\n";


        String phone = "13781826989\n" +
                "15972093781\n" +
                "13861237889\n" +
                "18520034881\n" +
                "13541282832\n" +
                "15366189917\n" +
                "15153192903\n" +
                "13612520095\n" +
                "17682164788\n" +
                "18894000986\n" +
                "18746952909\n" +
                "13752027380\n" +
                "18910100391\n" +
                "13510779529\n" +
                "13207221326\n" +
                "13810385613\n" +
                "18356576601\n" +
                "15116454557\n" +
                "15726685339\n" +
                "13799080042\n" +
                "18655429908\n" +
                "18677079799\n" +
                "18651632900\n" +
                "13149239235\n" +
                "15915857953\n" +
                "13923600549\n" +
                "15229002164\n" +
                "18090397468\n" +
                "15168651114\n" +
                "15110880213\n" +
                "15971081524\n" +
                "18795929494\n" +
                "13794511767\n" +
                "15962208430\n" +
                "18250829001\n" +
                "17603079013\n" +
                "13177772014\n" +
                "15073207655\n" +
                "13561410312\n" +
                "15216777309\n" +
                "19907422602\n" +
                "15905950517\n" +
                "18818279861\n" +
                "17773324478\n" +
                "15037501756\n" +
                "15559516055\n" +
                "18674024987\n" +
                "18550978612\n" +
                "18552622735\n" +
                "13652175700\n" +
                "13585156933\n" +
                "18780184334\n" +
                "15584677257\n" +
                "13571900975\n" +
                "18918598790\n" +
                "18733116414\n" +
                "15045892750\n" +
                "18237631076\n" +
                "18854178583\n" +
                "18617328167\n" +
                "18690882155\n" +
                "18677079799\n" +
                "15996200422\n" +
                "18652202295\n" +
                "15959156949\n" +
                "18686417726\n" +
                "13400661299\n" +
                "15637952018\n" +
                "13631411356\n" +
                "13680882361\n" +
                "18756929943\n" +
                "15670267526\n" +
                "13477030520\n" +
                "15088667044\n" +
                "18574402277\n" +
                "18709217172\n" +
                "13777589078\n" +
                "13682236694\n" +
                "13923681004\n" +
                "15242618136\n" +
                "13632391663\n" +
                "15845839288\n" +
                "13575416050\n" +
                "18665998890\n" +
                "13437072404\n" +
                "15314465530\n" +
                "15972987092\n" +
                "15994816112\n" +
                "13160880993\n" +
                "18251999719\n" +
                "13632998118\n" +
                "13816658125\n" +
                "18610117299\n" +
                "13810683409\n" +
                "17604372081\n" +
                "15298571229\n" +
                "17603079013\n" +
                "15229154440\n" +
                "18518756689\n" +
                "13418157552\n" +
                "18005266995\n" +
                "16608610816\n" +
                "18045335273\n" +
                "13868978973\n" +
                "13854089311\n" +
                "18795929494\n" +
                "15309291982\n" +
                "18177113070\n" +
                "18622767856\n" +
                "18783327719\n" +
                "18646085681\n" +
                "13641630995\n" +
                "15021745210\n" +
                "18194083982\n" +
                "15074598036\n" +
                "13094083366\n" +
                "18600425420\n" +
                "13828865119\n" +
                "15073207655\n" +
                "13760880403\n" +
                "15858264642\n" +
                "18520817361\n" +
                "13820639181\n" +
                "13877196951\n" +
                "18268181201\n" +
                "16601113489\n" +
                "18992592025\n" +
                "13753046103\n" +
                "15022644391\n" +
                "18674024987\n" +
                "13636715511\n" +
                "18826565286\n" +
                "13628136922\n" +
                "15228684350\n" +
                "13571900975\n" +
                "13985445865\n" +
                "18351816220\n" +
                "13632991829\n" +
                "18701777860\n" +
                "15102347886\n" +
                "17620602868\n" +
                "18250829001\n" +
                "13951679505\n" +
                "13787107133\n" +
                "13189869085\n" +
                "13367478084\n" +
                "15600442567\n" +
                "18612522137\n" +
                "18553976509\n" +
                "13585156933\n" +
                "13532222609\n" +
                "13588331481\n";

        String[] split = phone.split("\n");
        System.out.println(split.length);
        System.out.println(Arrays.asList(split));

        List<String> strings = Arrays.asList(split);

        List<String> sqls = new ArrayList<>();

        Set<String> sqlSet = new HashSet<>();
        String result = "";
        for (String string : strings) {
            String sqlTemp = sql;
            String s = sqlTemp.replace("#####", string);
            sqls.add(s);
            sqlSet.add(s);
        }

        System.out.println("set: " + sqlSet.size());
        StringBuilder sb = new StringBuilder();

        for (String s : sqls) {
            sb.append(s).append("\n").append("union").append("\n");
        }

        System.out.println(sb.toString());
    }
}
