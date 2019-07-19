package com.smartcity.smartcity.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.smartcity.smartcity.model.PlotType;
import com.smartcity.smartcity.model.enums.Category;

@Transactional
@Repository
public interface PlotTypeDAO extends JpaRepository<PlotType, Integer> {

	PlotType getPlotTypeByPlotTypeName(String plotTypeName);

	PlotType getPlotTypeByCategory(Category category);

	PlotType getPlotTypeByPlotTypeId(int plotTypeId);

	void deleteByPlotTypeId(int plotTypeId);
}
