package com.musinsa.payment.point.free.repository;

import com.musinsa.payment.point.free.model.Point;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FreePointRepository {

    int selectUsablePointAmt(String memberId);
    Point selectPointByPointSrl(long pointSrl);
    List<Point> selectUsablePointList(String memberId);
    int insertPoint(Point p);
    int updatePoint(Point p);
    void insertUsePoint(Point p);
    int updateUsePoint(Point p);
    List<Point> selectUsedPointListByPointSrl(long pointSrl);
    void insertCancelUsePoint(Point p);
    int updateCancelUsePoint(Point p);

}
