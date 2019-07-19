package com.smartcity.smartcity.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.smartcity.smartcity.model.Plot;

@Repository
public interface PlotDAO extends JpaRepository<Plot, Integer>, JpaSpecificationExecutor<Plot> {

	Plot getPlotByPlotName(String name);

	void deleteByPlotId(int plotId);

	void deleteAllByCityCityId(int cityId);

	Plot getPlotByCityCityIdAndPositionXAndPositionY(int cityId, int positionX, int positionY);

	List<Plot> findAllPlotByCityCityId(int cityId);

	List<Plot> findAllPlotByPlotTypePlotTypeAbrev(String plotTypeAbrev);

	List<Plot> findAllPlotByPlotTypePossibleValue(Boolean possible);

}
