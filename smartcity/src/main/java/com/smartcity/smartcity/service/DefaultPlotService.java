package com.smartcity.smartcity.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smartcity.smartcity.DAO.CityDAO;
import com.smartcity.smartcity.DAO.PlotDAO;
import com.smartcity.smartcity.algorithme.CityCase;
import com.smartcity.smartcity.algorithme.ValCase;
import com.smartcity.smartcity.assembler.PlotAssembler;
import com.smartcity.smartcity.model.City;
import com.smartcity.smartcity.model.Plot;
import com.smartcity.smartcity.model.enums.InfluenceLevel;
import com.smartcity.smartcity.representation.PlotRepresentation;

@Transactional
@Service
public class DefaultPlotService implements PlotService {
	@Autowired
	PlotDAO plotDAO;

	@Autowired
	CityDAO cityDAO;

	@Autowired
	PlotAssembler plotAssembler;

	@Transactional
	public float[][] InfluenceCasePrecentage(int cityId) {

		List<CityCase> cityCases = modeleParCasesDepuisRTB(cityId);
		cityCases = longueurParCases(cityCases);
		float[][] casePrecentageTable = PercentageInfluenceParCases(cityCases, cityId);

		return casePrecentageTable;

	}

	@Transactional
	public void calculateEuclidDistance(int cityId) {
		City city = cityDAO.findOne(cityId);
		List<Plot> PlotsFound1 = new ArrayList<>();
		PlotsFound1 = plotDAO.findAllPlotByCityCityId(cityId);
		int euclideDist = city.getEuclidDist();

		for (Plot pt : PlotsFound1) {
			int posdistanceInflu = pt.getPlotType().getPosDistInfluence();
			int negdistanceInflu = pt.getPlotType().getNegativeInfluence();
			if (posdistanceInflu > euclideDist) {
				euclideDist = posdistanceInflu;
			}
			if (negdistanceInflu > euclideDist) {
				euclideDist = negdistanceInflu;
			}
		}
		city.setEuclidDist(euclideDist);
		cityDAO.save(city);
	}

	@Transactional
	public void calculatePlotFinalValueByCityId(int cityId) {
		City city = cityDAO.findOne(cityId);

		int eucludianDistanceMax = city.getEuclidDist();
		int cityWidth = city.getWidth();
		int cityHeight = city.getHeight();

		List<Plot> PlotsFound = plotDAO.findAllPlotByCityCityId(cityId);
		for (Plot pt : PlotsFound) {
			int xp = pt.getPositionX();
			int yp = pt.getPositionY();
			float p = 0;
			float valeur = 0F;
			if (pt.getPlotType().isPossibleValue()) {
				// La case peut prendre une valeur
				for (int x = xp - eucludianDistanceMax; x < xp + eucludianDistanceMax; x++) {
					for (int y = yp - eucludianDistanceMax; y < yp + eucludianDistanceMax; y++) {
						if (x < 0 || y < 0 || y > cityHeight || x >= cityWidth || y >= cityHeight) {
							// On ne fait rien
						} else {
							Plot pXY = plotDAO.getPlotByCityCityIdAndPositionXAndPositionY(cityId, x, y);

							if (pXY.getPlotType().getInfluenceLevel().equals(InfluenceLevel.EUCLIDIEN)) {

								float dist = (float) Math.sqrt((xp - x) * (xp - x) + (yp - y) * (yp - y));

								if ((dist <= pXY.getPlotType().getPosDistInfluence())
										&& (pXY.getPlotId() != pt.getPlotId())) {
									p = p + pXY.getPlotType().getPositiveInfluence()
											* (pXY.getPlotType().getPosDistInfluence() - dist)
											/ pXY.getPlotType().getPosDistInfluence();
								}

								if ((dist <= pXY.getPlotType().getNegDistInfluence())
										&& (pXY.getPlotId() != pt.getPlotId())) {
									p = p - pXY.getPlotType().getNegativeInfluence()
											* (pXY.getPlotType().getNegDistInfluence() - dist)
											/ pXY.getPlotType().getNegDistInfluence();
								}
							} else if (pXY.getPlotType().getInfluenceLevel().equals(InfluenceLevel.CASES)) { 
								float[][] casePrecentageTable = InfluenceCasePrecentage(cityId); p = p +
								casePrecentageTable[xp][yp];
								System.out.println("p avec route et route avec bus :" +
								casePrecentageTable[xp][yp]); System.out.println("xp=" + xp + " - " + "yp=" +
								yp); 
							}
						}
					}
				}
				if (p < -100) {
					p = -100;
				} else if (p > 100) {
					p = 100;
				}
				valeur = pt.getInitialValue() + p * (pt.getCity().getMaximumValue() - pt.getInitialValue()) / 100;
			}
			pt.setFinalValue(valeur);
			plotDAO.save(pt);
		}
	}

	@Transactional
	public void plotChangeColor(int cityId) {
		City city = cityDAO.findOne(cityId);
		float minValue = city.getMinimumValue();
		float maxValue = city.getMaximumValue();
		float c1 = minValue + ((maxValue - minValue) / 5) * 1;
		float c2 = minValue + ((maxValue - minValue) / 5) * 2;
		float c3 = minValue + ((maxValue - minValue) / 5) * 3;
		float c4 = minValue + ((maxValue - minValue) / 5) * 4;

		String color1 = "#2B511A";
		String color2 = "#407927";
		String color3 = "#579835";
		String color4 = "#62A73B";
		String color5 = "#89C765";
		List<Plot> PlotsFound = plotDAO.findAllPlotByCityCityId(cityId);
		for (Plot pt : PlotsFound) {
			float finalValue = pt.getFinalValue();
			if (finalValue < minValue) {
				pt.setColor("#f2f2f2");
				plotDAO.save(pt);
			} else if ((finalValue >= minValue) && (finalValue < c1)) {
				pt.setColor(color5);
				plotDAO.save(pt);
			} else if ((finalValue >= c1) && (finalValue < c2)) {
				pt.setColor(color4);
				plotDAO.save(pt);
			} else if ((finalValue >= c2) && (finalValue < c3)) {
				pt.setColor(color3);
				plotDAO.save(pt);
			} else if ((finalValue >= c3) && (finalValue < c4)) {
				pt.setColor(color2);
				plotDAO.save(pt);
			} else {
				pt.setColor(color1);
				plotDAO.save(pt);
			}

		}

	}

	@Transactional
	@Override
	public PlotRepresentation getPlotRepresentationByPlotId(int plotId) {

		Plot plotFound = plotDAO.findOne(plotId);

		PlotRepresentation plotRepresentation = plotAssembler.createRepresentation(plotFound);

		return plotRepresentation;

	}

	@Transactional
	@Override
	public List<PlotRepresentation> getAllPlotRepresentationByCityId(int cityId) {

		List<Plot> plotFound = plotDAO.findAllPlotByCityCityId(cityId);

		List<PlotRepresentation> plotRepresentationFound = new ArrayList<PlotRepresentation>();

		for (Plot pt : plotFound) {
			plotRepresentationFound.add(this.getPlotRepresentationByPlotId(pt.getPlotId()));
		}
		return plotRepresentationFound;
	}

	public Plot[][] tabPlot(List<Plot> lp) {

		// même si la liste de Plot est mal rangée, le tableau de Plot le sera!
		int longueur = lp.get(0).getCity().getWidth();
		int hauteur = lp.get(0).getCity().getHeight();

		Plot[][] tp = new Plot[longueur][hauteur];

		for (int y = 0; y < hauteur; y++) {

			for (int x = 0; x < longueur; x++) {

				int k = 0;
				for (Plot pt : lp) {

					if ((pt.getPositionX() == x) && (pt.getPositionY() == y)) {

						tp[x][y] = lp.get(k);
					}
					k++;
				}
			}
		}
		return tp;
	}

	public static List<Plot> ListOrdreXY(List<Plot> Plotlist) {

		List<Plot> rep = new ArrayList<Plot>();

		int longueur = Plotlist.get(0).getCity().getWidth();
		int hauteur = Plotlist.get(0).getCity().getHeight();

		for (int y = 0; y < hauteur; y++) {

			for (int x = 0; x < longueur; x++) {

				int k = 0;
				for (Plot pt : Plotlist) {

					if ((pt.getPositionX() == x) && (pt.getPositionY() == y)) {

						rep.add(Plotlist.get(k));
					}
					k++;
				}
			}
		}

		Plotlist = rep;

		return Plotlist;
	}

	@Transactional
	@Override
	public List<CityCase> modeleParCasesDepuisRTB(int cityId) {

		City city = cityDAO.findOne(cityId);
		int maxCase = city.getDistPerBox();
		int longueur = city.getWidth();
		int hauteur = city.getHeight();

		List<CityCase> cityCaseList = new ArrayList<CityCase>();
		List<Plot> plotRTBList = new ArrayList<Plot>();

		List<Plot> plotFound = plotDAO.findAllPlotByCityCityId(cityId);
		Plot[][] plotTable = tabPlot(plotFound);

		plotRTBList = plotDAO.findAllPlotByPlotTypePlotTypeAbrev("RTB");

		if (plotRTBList.size() == 0) {
			return cityCaseList;
		} else {
			int dmax = plotRTBList.get(0).getPlotType().getPosDistInfluence();

			for (Plot plotRTB : plotRTBList) {
				ValCase header = new ValCase();
				ValCase[][] valCaseTable = new ValCase[longueur][hauteur];
				for (int y = 0; y < hauteur; y++) {

					for (int x = 0; x < longueur; x++) {

						if (plotTable[x][y].getPlotType().getPlotTypeAbrev().contentEquals("RTE")) {

							valCaseTable[x][y] = new ValCase(-1, dmax, maxCase);
						} else if (plotTable[x][y].getPlotType().getPlotTypeAbrev().contentEquals("RTB")) {

							if (plotTable[x][y] == plotRTB) {

								valCaseTable[x][y] = new ValCase(0, dmax, maxCase);
								header = valCaseTable[x][y];
							} else {

								valCaseTable[x][y] = new ValCase(-1, dmax, maxCase);
							}
						} else {
							// this.valider reste à false
							valCaseTable[x][y] = new ValCase(dmax, maxCase);
						}

						valCaseTable[x][y].setVx(x);
						valCaseTable[x][y].setVy(y);
					}
				}
				CityCase cityCase = new CityCase(valCaseTable, header);
				cityCaseList.add(cityCase);
			}

			return cityCaseList;
		}

	}

	public List<CityCase> longueurParCases(List<CityCase> cityCaseList) {

		for (CityCase cityCase : cityCaseList) {

			cityCase.longueurParCasesDepuisRTB();
		}

		return cityCaseList;
	}

	@Transactional
	public float[][] PercentageInfluenceParCases(List<CityCase> cityCaseList, int cityId) {

		City city = cityDAO.findOne(cityId);
		int longueur = city.getWidth();
		int hauteur = city.getHeight();

		List<Plot> plotFoundList = plotDAO.findAllPlotByCityCityId(cityId);

		// on range plotFoundList, pour être sûr d'avoir l'ordre par xy
		plotFoundList = ListOrdreXY(plotFoundList);

		// Liste de Plot avec possibleValue à true
		List<Plot> plotFoundPossibleValueList = plotDAO.findAllPlotByPlotTypePossibleValue(true);
		plotFoundPossibleValueList = ListOrdreXY(plotFoundPossibleValueList);

		float[][] pxy = new float[longueur][hauteur];

		if ((cityCaseList.size() == 0) || (plotFoundPossibleValueList.size() == 0)) {

			return pxy;
		} else {

			int d = 0;
			int dmax = cityCaseList.get(0).getTetecherche().getDmax();

			List<Plot> plotRTBList = new ArrayList<Plot>();
			plotRTBList = plotDAO.findAllPlotByPlotTypePlotTypeAbrev("RTB");

			// une façon de récupérer pmax
			float pmax = (float) plotRTBList.get(0).getPlotType().getPositiveInfluence();

			for (Plot pt : plotFoundPossibleValueList) {

				float p = 0f;

				for (CityCase cityCase : cityCaseList) {

					d = cityCase.dParCasesMinDepuisRTB(pt);
					p = p + pmax * (dmax - d) / dmax;
				}

				pxy[pt.getPositionX()][pt.getPositionY()] = p;
			}
		}

		return pxy;
	}
}