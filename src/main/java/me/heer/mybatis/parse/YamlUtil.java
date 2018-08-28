package me.heer.mybatis.parse;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;
/**
 * @author heer_
 * @date 2018��8��10�� - ����6:04:06
 * @Desc
 */
public class YamlUtil {
	@SuppressWarnings("unchecked")
	public static Map<String, Object> parseYaml(String yaml, String rootNode) {
		try {
			FileInputStream fis = new FileInputStream(yaml);
			Yaml yml = new Yaml();
			Map<String, Object> loaded = (Map<String, Object>) yml.load(fis);
			return (Map<String, Object>)loaded.get(rootNode);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

}
