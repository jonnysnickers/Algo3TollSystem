package LTS;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class OurMagicGenericQuery {

	public String doQuery(String query) throws ParseException {

		/** Handling SELECT part **/
		int j = query.indexOf(")");
		String con = query.substring(7, j);
		Set<Toll> afterSelect = select(con);

		/** Handling GROUP() part, setting NoGrouping if no GROUP **/
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

		/** Handling SUM/COUNT/MIN()/MAX() **/
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
			/** Case when there is no SUM/COUNT/MIN()/MAX() **/
			StringBuilder sb = new StringBuilder("");
			for (Map.Entry<String, Set<Toll>> entry : afterGroup.entrySet()) {
				Set<Toll> tollset = entry.getValue();
				sb.append(entry.getKey() + ":\n");
				for (Toll toll : tollset) {
					sb.append(toll.toString());
					//toll.write();
				}
			}
			return sb.toString();
		}
	}

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

	public String OMGSum(Map<String, Set<Toll>> source) {
		StringBuilder sb = new StringBuilder("");
		for (Map.Entry<String, Set<Toll>> entry : source.entrySet()) {
			Double sum = 0.0;
			Set<Toll> tollset = entry.getValue();
			sb.append(entry.getKey() + ": ");
			for (Toll toll : tollset) {
				sum += toll.getGold();
			}
			sb.append(sum + "\n");
		}
		return sb.toString();
	}

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

	public String OMGMin(Map<String, Set<Toll>> source, String field) throws ParseException {
		StringBuilder sb = new StringBuilder("");
		if (field.equals("plate")) {
			return "This query makes no sense!";
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
				sb.append(res + "\n");
			}
			return sb.toString();
		}
		if (field.equals("road")) {
			return "This query makes no sense!";
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

	public String OMGMax(Map<String, Set<Toll>> source, String field) throws ParseException {
		StringBuilder sb = new StringBuilder("");
		if (field.equals("plate")) {
			return "This query makes no sense!";
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
				sb.append(res + "\n");
			}
			return sb.toString();
		}
		if (field.equals("road")) {
			return "This query makes no sense!";
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
