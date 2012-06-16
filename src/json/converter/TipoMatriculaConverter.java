package json.converter;

import modelo.constante.TipoMatricula;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public class TipoMatriculaConverter implements Converter {
	@Override
    @SuppressWarnings("rawtypes")
    public boolean canConvert(Class type) {
		return TipoMatricula.class.isAssignableFrom(type);
    }
	 
	@Override
	public void marshal(Object value, HierarchicalStreamWriter writer,MarshallingContext context) {
	    TipoMatricula tipoMatricula = (TipoMatricula) value;
	    writer.startNode("nome");
	    writer.setValue(tipoMatricula.getNome());
	    writer.endNode();
	 }

	@Override
	public Object unmarshal(HierarchicalStreamReader reader,UnmarshallingContext context) {
		return null;
	}

}
