package com.lorenzo.mind_palace.mapper;

import org.apache.ibatis.annotations.Param;

/**
 * @author libocheng
 */
public interface DocMapperCount {

    public void increaseViewCount(@Param("id") Long id);

    public void increaseVoteCount(@Param("id") Long id);

    public void updateEbookInfo();
}
