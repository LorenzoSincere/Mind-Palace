package com.lorenzo.mind_palace.mapper;

import com.lorenzo.mind_palace.response.StatisticResp;

import java.util.List;

public interface EbookSnapshotMapperCount {

    public void genSnapshot();

    List<StatisticResp> getStatistic();

    List<StatisticResp> get30Statistic();
}
