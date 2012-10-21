package json.converter;

import modelo.constante.TipoAula;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public class TipoAulaConverter implements Converter {
	@Override
	@SuppressWarnings("rawtypes")
	public boolean canConvert(Class type) {
		return TipoAula.class.isAssignableFrom(type);
	}
	
	@Override
	public void marshal(Object value, HierarchicalStreamWriter writer, MarshallingContext context) {
		TipoAula tipoAula = (TipoAula) value;
		writer.startNode("nome");
		writer.setValue(tipoAula.getNome());
		writer.endNode();
	}
	
	@Override
	public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
		return null;
	}
	
}
