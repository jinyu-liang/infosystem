package com.ailk.ims.mapreduce;

import java.util.List;
import com.ailk.easyframe.route.client.RouterClient;
import com.ailk.easyframe.sal.exchange.SalResponse;
import com.ailk.easyframe.sal.mapreduce.Entry;
import com.ailk.easyframe.sal.mapreduce.MapReduce;
import com.ailk.easyframe.sal.route.bean.MdbRoute;
import com.ailk.ims.util.SpringUtil;

public class QueryAbmTableMapReduce<I, O> implements MapReduce<I, O>
{

    private RouterClient routeService = SpringUtil.getRouteSearchService();

    @Override
    public List<Entry<MdbRoute, I>> mapping(String verticalValue, I arg1)
    {
        /*
        SAbmBalanceQueryUp sAbmBalanceQueryUp = (SAbmBalanceQueryUp) arg1;
        Long acctId = sAbmBalanceQueryUp.get_acctId();
        RouteDimension rdm = new RouteDimension();
        rdm.setAccountId(acctId);
        RouteResult result = routeService.searchMdbRoutingInfo(verticalValue, rdm);
        List<Entry<MdbRoute, I>> list = new ArrayList<Entry<MdbRoute, I>>();
        list.add(new com.ailk.easyframe.sal.mapreduce.Entry<MdbRoute, I>(result, (I) arg1));
        return list;
        */
        return null;
    }

    @Override
    public O reduce(I arg0, List<SalResponse<I, O>> list)
    {
        /*
        SAbmBalanceQueryRet sAbmBalanceQueryRet = null;
        if (CommonUtil.isNotEmpty(list))
        {
            SalResponse<I, O> resp=list.get(0);
            sAbmBalanceQueryRet = (SAbmBalanceQueryRet) resp.getOut();
        }
        return (O) sAbmBalanceQueryRet;
*/
        return null;
    }

}
