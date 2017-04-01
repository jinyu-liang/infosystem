package com.ailk.ims.listener;

import com.ailk.easyframe.transaction.support.CpfTransactionListener;

import jef.database.support.DbOperatorListener;

public interface IDBOperatorListener extends CpfTransactionListener
{
    public void clearServiceFlow();
    
    public void addActionContainer(IMSListenerServiceFlow bean);
}
