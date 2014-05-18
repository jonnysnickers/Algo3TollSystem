package LTS;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class OurMagicGenericQuery {
	
	/**
	 * 
	 * @param query - Query in form SELECT(conditions)[GROUP(field)][COMMAND].
	 * Things in [] are optional. COMMAND is one from MIN(field)/MAX(field)/COUNT/SUM.
	 * "field" is one from road/gold/plate/enterDate/exitDate.
	 * conditions is string in form "field(<>=)value[&field(<>=)value...]".
	 * Value for date is in form "yyyy-MM-dd'T'HH:mm:ss" for example "2000-10-10T10:10:10".
	 * Plates are compared lexicographically.
	 * For Road there is only = operator (typing < or > is considered as =).
	 * @return String that is answer to given query.
	 * @throws ParseException
	 */
	public String doQuery(String query) throws ParseException {

		/** Handling SELECT part **/
		int j = query.indexOf(")");
		String con = query.substring(7, j);
		Set<Toll> afterSelect = select(con);

		/** Handling GROUP(field) part, setting NoGrouping if no GROUP or GROUP() **/
		Map<String, Set<Toll>> afterGroup = null;

		if (query.contains("GROUP(plate)")) {
			afterGroup = groupByPlate(afterSelect);
		} else if (query.contains("GROUP(gold)")) {
			afterGroup = groupByGold(afterSelect);
		} else if (query.contains("GROUP(road)")) {
			afterGroup = groupByRoad(afterSelect);
		} else if (query.contains("GROUP(enterDate)")) {
			afterGroup = groupByEnterDate(afterSelect);
		} else if (query.contains("GROUP(exitDate)")) {
			afterGroup = groupByExitDate(afterSelect);
		} else {
			afterGroup = new HashMap<String, Set<Toll>>();
			afterGroup.put("NoGrouping", afterSelect);
		}

		/** Handling SUM/COUNT/MIN(field)/MAX(field) **/
		if (query.contains("SUM")) {
			return OMGSum(afterGroup);
		} else if (query.contains("COUNT")) {
			return OMGCount(afterGroup);
		} else if (query.contains("MIN")) {
			int i = query.indexOf("MIN");
			i = query.indexOf("(", i) + 1;
			j = query.indexOf(")", i);
			return OMGMin(afterGroup, query.substring(i, j));
		} else if (query.contains("MAX")) {
			int i = query.indexOf("MAX");
			i = query.indexOf("(", i) + 1;
			j = query.indexOf(")", i);
			return OMGMax(afterGroup, query.substring(i, j));
		} else {
			/** Case in which there is no SUM/COUNT/MIN()/MAX() **/
			StringBuilder sb = new StringBuilder("");
			for (Map.Entry<String, Set<Toll>> entry : afterGroup.entrySet()) {
				Set<Toll> tollset = entry.getValue();
				sb.append(entry.getKey() + ":\n");
				for (Toll toll : tollset) {
					sb.append(toll.toString());
				}
			}
			return sb.toString();
		}
	}
	
	/**
	 * This function is responsible for grouping Set of Tolls into Subsets by Roads.
	 * @param source - Set of Tolls to be grouped.
	 * @return Map<String,Set<Toll>>res - Sets of Tolls grouped by Roads.
	 */
	public Map<String, Set<Toll>> groupByRoad(Set<Toll> source) {
		Map<String, Set<Toll>> res = new HashMap<String, Set<Toll>>();

		for (Toll toll : source) {
			if (res.containsKey(((Integer) toll.getRoad().getId()).toString())) {
				res.get(((Integer) toll.getRoad().getId()).toString()).add(toll);
			} else {
				Set<Toll> tmp = new HashSet<Toll>();
				tmp.add(toll);
				res.put(((Integer) toll.getRoad().getId()).toString(), tmp);
			}
		}
		return res;
	}

	/**
	 * This function is responsible for grouping Set of Tolls into Subsets by Plates.
	 * @param source - Set of Tolls to be grouped.
	 * @return Map<String,Set<Toll>>res - Sets of Tolls grouped by Plates.
	 */
	public Map<String, Set<Toll>> groupByPlate(Set<Toll> source) {
		Map<String, Set<Toll>> res = new HashMap<String, Set<Toll>>();

		for (Toll toll : source) {
			if (res.containsKey(toll.getPlate())) {
				res.get(toll.getPlate()).add(toll);
			} else {
				Set<Toll> tmp = new HashSet<Toll>();
				tmp.add(toll);
				res.put(toll.getPlate(), tmp);
			}
		}
		return res;
	}

	/**
	 * This function is responsible for grouping Set of Tolls into Subsets by Gold.
	 * @param source - Set of Tolls to be grouped.
	 * @return Map<String,Set<Toll>>res - Sets of Tolls grouped by Gold.
	 */
	public Map<String, Set<Toll>> groupByGold(Set<Toll> source) {
		Map<String, Set<Toll>> res = new HashMap<String, Set<Toll>>();

		for (Toll toll : source) {
			if (res.containsKey(Double.toString(toll.getGold()))) {
				res.get(Double.toString(toll.getGold())).add(toll);
			} else {
				Set<Toll> tmp = new HashSet<Toll>();
				tmp.add(toll);
				res.put(Double.toString(toll.getGold()), tmp);
			}
		}
		return res;
	}

	/**
	 * This function is responsible for grouping Set of Tolls into Subsets by EnterDates.
	 * @param source - Set of Tolls to be grouped.
	 * @return Map<String,Set<Toll>>res - Sets of Tolls grouped by EnterDates.
	 */
	public Map<String, Set<Toll>> groupByEnterDate(Set<Toll> source) {
		Map<String, Set<Toll>> res = new HashMap<String, Set<Toll>>();

		for (Toll toll : source) {
			if (res.containsKey(toll.getEnterDate().toString())) {
				res.get(toll.getEnterDate().toString()).add(toll);
			} else {
				Set<Toll> tmp = new HashSet<Toll>();
				tmp.add(toll);
				res.put(toll.getEnterDate().toString(), tmp);
			}
		}
		return res;
	}

	/**
	 * This function is responsible for grouping Set of Tolls into Subsets by ExitDates.
	 * @param source - Set of Tolls to be grouped.
	 * @return Map<String,Set<Toll>>res - Sets of Tolls grouped by ExitDates.
	 */
	public Map<String, Set<Toll>> groupByExitDate(Set<Toll> source) {
		Map<String, Set<Toll>> res = new HashMap<String, Set<Toll>>();

		for (Toll toll : source) {
			if (res.containsKey(toll.getExitDate().toString())) {
				res.get(toll.getExitDate().toString()).add(toll);
			} else {
				Set<Toll> tmp = new HashSet<Toll>();
				tmp.add(toll);
				res.put(toll.getExitDate().toString(), tmp);
			}
		}
		return res;
	}

	/**
	 * This function is responsible for summing gold in sets of Tolls.
	 * @param source - Sets of Tolls grouped by some criteria.
	 * @return String containing 'names' by witch sets are grouped followed by sum of gold in corresponding sets.
	 */
	public String OMGSum(Map<String, Set<Toll>> source) {
		StringBuilder sb = new StringBuilder("");
		for (Map.Entry<String, Set<Toll>> entry : source.entrySet()) {
			Double sum = 0.0;
			Set<Toll> tollset = entry.getValue();
			sb.append(entry.getKey() + ": ");
			for (Toll toll : tollset) {
				sum += toll.getGold();
			}
			sb.append(Math.round(sum*100)/100.0 + "\n");
		}
		return sb.toString();
	}

	/**
	 * This function is responsible for counting entries in sets of Tolls.
	 * @param source - Sets of Tolls grouped by some criteria.
	 * @return String containing 'names' by witch sets are grouped followed by number of record in corresponding sets.
	 */
	public String OMGCount(Map<String, Set<Toll>> source) {
		StringBuilder sb = new StringBuilder("");
		for (Map.Entry<String, Set<Toll>> entry : source.entrySet()) {
			int counter = 0;
			Set<Toll> tollset = entry.getValue();
			sb.append(entry.getKey() + ": ");
			for (@SuppressWarnings("unused") Toll toll : tollset) {
				counter++;
			}
			sb.append(counter + "\n");
		}
		return sb.toString();
	}

	/**
	 * This function is responsible for selecting minimum of given field in sets of Tolls.
	 * @param source - Sets of Tolls grouped by some criteria.
	 * @param field - name of field in which we search for minimum.
	 * @return String containing 'names' by witch sets are grouped followed by minimum field in corresponding sets. Query for minimum plate and road makes no sense.
	 * @throws ParseException
	 */
	public String OMGMin(Map<String, Set<Toll>> source, String field) throws ParseException {
		StringBuilder sb = new StringBuilder("");
		if (field.equals("plate")) {
			return "This query makes no sense!\n";
		}
		if (field.equals("gold")) {
			for (Map.Entry<String, Set<Toll>> entry : source.entrySet()) {
				Double res = Double.MAX_VALUE;
				Set<Toll> tollset = entry.getValue();
				sb.append(entry.getKey() + ": ");
				for (Toll toll : tollset) {
					if (res > toll.getGold()) {
						res = toll.getGold();
					}
				}
				sb.append(Math.round(res*100)/100.0 + "\n");
			}
			return sb.toString();
		}
		if (field.equals("road")) {
			return "This query makes no sense!\n";
		}
		if (field.equals("enterDate")) {
			for (Map.Entry<String, Set<Toll>> entry : source.entrySet()) {
				Date date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse("2100-12-10T10:10:10");
				Set<Toll> tollset = entry.getValue();
				sb.append(entry.getKey() + ": ");
				for (Toll toll : tollset) {
					if (date.after(toll.getEnterDate())) {
						date = toll.getEnterDate();
					}
				}
				sb.append(date + "\n");
			}
			return sb.toString();
		}
		if (field.equals("exitDate")) {
			for (Map.Entry<String, Set<Toll>> entry : source.entrySet()) {
				Date date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse("2100-12-10T10:10:10");
				Set<Toll> tollset = entry.getValue();
				sb.append(entry.getKey() + ": ");
				for (Toll toll : tollset) {
					if (date.after(toll.getExitDate())) {
						date = toll.getExitDate();
					}
				}
				sb.append(date + "\n");
			}
			return sb.toString();
		}
		return null;
	}

	/**
	 * This function is responsible for selecting maximum of given field in sets of Tolls.
	 * @param source - Sets of Tolls grouped by some criteria.
	 * @param field - name of field in which we search for maximum.
	 * @return String containing 'names' by witch sets are grouped followed by maximum field in corresponding sets. Query for minimum plate and road makes no sense.
	 * @throws ParseException
	 */
	public String OMGMax(Map<String, Set<Toll>> source, String field) throws ParseException {
		StringBuilder sb = new StringBuilder("");
		if (field.equals("plate")) {
			return "This query makes no sense!\n";
		}
		if (field.equals("gold")) {
			for (Map.Entry<String, Set<Toll>> entry : source.entrySet()) {
				Double res = Double.MIN_VALUE;
				Set<Toll> tollset = entry.getValue();
				sb.append(entry.getKey() + ": ");
				for (Toll toll : tollset) {
					if (res < toll.getGold()) {
						res = toll.getGold();
					}
				}
				sb.append(Math.round(res*100)/100.0 + "\n");
			}
			return sb.toString();
		}
		if (field.equals("road")) {
			return "This query makes no sense!\n";
		}
		if (field.equals("enterDate")) {
			for (Map.Entry<String, Set<Toll>> entry : source.entrySet()) {
				Date date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse("2000-12-10T10:10:10");
				Set<Toll> tollset = entry.getValue();
				sb.append(entry.getKey() + ": ");
				for (Toll toll : tollset) {
					if (date.before(toll.getEnterDate())) {
						date = toll.getEnterDate();
					}
				}
				sb.append(date + "\n");
			}
			return sb.toString();
		}
		if (field.equals("exitDate")) {
			for (Map.Entry<String, Set<Toll>> entry : source.entrySet()) {
				Date date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse("2000-12-10T10:10:10");
				Set<Toll> tollset = entry.getValue();
				sb.append(entry.getKey() + ": ");
				for (Toll toll : tollset) {
					if (date.before(toll.getExitDate())) {
						date = toll.getExitDate();
					}
				}
				sb.append(date + "\n");
			}
			return sb.toString();
		}
		return null;
	}

	/**
	 * This function is responsible for selecting toll that satisfy given criteria from all Tolls stored in DataBase.finished.
	 * @param con - criteria of search in form "field(<>=)value". Connected by & if more than one.
	 * @return Set<Toll>result - set of Tolls satisfying con.
	 * @throws ParseException
	 */
	public Set<Toll> select(String con) throws ParseException {

		String[] conditions = con.split("&");

		Set<Toll> result = new HashSet<Toll>();

		for (Toll toll : DataBase.getInstance().finished) {

			Boolean pass = true;
			for (String condition : conditions) {

				char operation;
				String[] tmp;

				if (condition.contains("=")) {
					operation = '=';
					tmp = condition.split("=");
				} else if (condition.contains(">")) {
					operation = '>';
					tmp = condition.split(">");
				} else {
					operation = '<';
					tmp = condition.split("<");
				}
				
				if(tmp.length != 2)continue;
				
				if (tmp[0].equals("road")) {
					if (!tmp[1].equals(((Integer) toll.getRoad().getId()).toString())) {
						pass = false;
					}
				} else if (tmp[0].equals("gold")) {
					if (operation == '>' && toll.getGold() < Double.parseDouble(tmp[1])) {
						pass = false;
					}
					if (operation == '<' && toll.getGold() > Double.parseDouble(tmp[1])) {
						pass = false;
					}
					if (operation == '=' && toll.getGold() != Double.parseDouble(tmp[1])) {
						pass = false;
					}
				} else if (tmp[0].equals("plate")) {
					if (operation == '>' && (toll.getPlate().compareTo(tmp[1]) < 0)) {
						pass = false;
					}
					if (operation == '<' && (toll.getPlate().compareTo(tmp[1]) > 0)) {
						pass = false;
					}
					if (operation == '=' && !toll.getPlate().equals(tmp[1])) {
						pass = false;
					}
				} else if (tmp[0].equals("enterDate")) {
					if (operation == '>'
							&& (new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(tmp[1]).compareTo(toll.getEnterDate()) > 0)) {
						pass = false;
					}
					if (operation == '<'
							&& (new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(tmp[1]).compareTo(toll.getEnterDate()) < 0)) {
						pass = false;
					}
					if (operation == '='
							&& (new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(tmp[1]).compareTo(toll.getEnterDate()) != 0)) {
						pass = false;
					}
				} else if (tmp[0].equals("exitDate")) {
					if (operation == '>'
							&& (new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(tmp[1]).compareTo(toll.getExitDate()) > 0)) {
						pass = false;
					}
					if (operation == '<'
							&& (new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(tmp[1]).compareTo(toll.getExitDate()) < 0)) {
						pass = false;
					}
					if (operation == '='
							&& (new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(tmp[1]).compareTo(toll.getExitDate()) != 0)) {
						pass = false;
					}
				}
			}

			if (pass) {
				result.add(toll);
			}
		}
		return result;
	}

}
