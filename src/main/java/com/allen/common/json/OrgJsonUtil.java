package com.allen.common.json;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;
import java.util.Set;

public class OrgJsonUtil {
	public static void main(String[] args) {
		try {
			String json = "{\"1293142@1098820@1211867_1083940_1293396_1148650\":[{\"FS_ID\":\"1293396\",\"SX_MC\":\"李四\",\"YFS_ID\":\"1293142\",\"SZ_ID\":\"1148650\",\"SX_Z\":\"803932\"}],\"1293142@1098820@1211867_1083940_1293396_1097084\":[{\"FS_ID\":\"1293396\",\"SX_MC\":\"李四\",\"YFS_ID\":\"1293142@1084106@1103027\",\"SZ_ID\":\"1097084\",\"SX_Z\":\"124\"}],\"1293142@1098820@1211867_1083940_1293331_1148650\":[{\"FS_ID\":\"1293331\",\"SX_MC\":\"张三\",\"YFS_ID\":\"1293142\",\"SZ_ID\":\"1148650\",\"SX_Z\":\"1005240\"}],\"1293142@1098820@1211867_1083940_1293331_1097084\":[{\"FS_ID\":\"1293331\",\"SX_MC\":\"张三\",\"YFS_ID\":\"1293142@1084106@1103027\",\"SZ_ID\":\"1097084\",\"SX_Z\":\"204\"}],\"1293142@1098820@1211867_1083940_1284806_1148650\":[{\"FS_ID\":\"1284806\",\"SX_MC\":\"王小二\",\"YFS_ID\":\"1293142\",\"SZ_ID\":\"1148650\",\"SX_Z\":\"513475\"}],\"1293142@1098820@1211867_1083940_1284806_1097084\":[{\"FS_ID\":\"1284806\",\"SX_MC\":\"王小二\",\"YFS_ID\":\"1293142@1084106@1103027\",\"SZ_ID\":\"1097084\",\"SX_Z\":\"145\"}],\"1293142@1098820@1211867_1083940_1293461_1148650\":[{\"FS_ID\":\"1293461\",\"SX_MC\":\"朱重八\",\"YFS_ID\":\"1293142\",\"SZ_ID\":\"1148650\",\"SX_Z\":\"339040\"}],\"1293142@1098820@1211867_1083940_1293461_1097084\":[{\"FS_ID\":\"1293461\",\"SX_MC\":\"朱重八\",\"YFS_ID\":\"1293142@1084106@1103027\",\"SZ_ID\":\"1097084\",\"SX_Z\":\"52\"}]}";
			JSONObject jsonObj = new JSONObject(json);

			// 方式二
			Iterator it = jsonObj.keys();
			for (int j = 0; it.hasNext(); j++) {
				String str = it.next().toString();
				System.out.println(str);

				JSONArray array = new JSONArray(jsonObj.get(str).toString());
				System.out.println(array);
				for (int i = 0; i < array.length(); i++) {
					JSONObject jons = array.getJSONObject(i);
					System.out.println(jons.get("SX_MC"));
				}
			}
			System.out.println("tep2");

			// 方式1
			Set<String> set = jsonObj.keySet();
			for (String str : set) {
				System.out.println(str);
				JSONArray array = new JSONArray(jsonObj.get(str).toString());
				System.out.println(array);
				for (int i = 0; i < array.length(); i++) {
					JSONObject jons = array.getJSONObject(i);
					System.out.println(jons.get("SX_MC"));
				}
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
}
