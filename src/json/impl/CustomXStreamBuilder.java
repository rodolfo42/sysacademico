package json.impl;
import br.com.caelum.vraptor.interceptor.TypeNameExtractor;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.PrototypeScoped;
import br.com.caelum.vraptor.serialization.xstream.XStreamBuilderImpl;
import br.com.caelum.vraptor.serialization.xstream.XStreamConverters;

import com.thoughtworks.xstream.XStream;


@PrototypeScoped
@Component
public class CustomXStreamBuilder extends XStreamBuilderImpl {


    public CustomXStreamBuilder(XStreamConverters converters,TypeNameExtractor extractor) {
		super(converters, extractor);
		// TODO Auto-generated constructor stub
	}

	//delega o construtor


    public XStream configure(XStream xstream) {



	super.configure(xstream);


        xstream.aliasSystemAttribute(null, "class");


        xstream.aliasSystemAttribute(null, "resolves-to");


	return xstream;


    }
    
    @Override
    public XStream jsonInstance() {
        XStream xstream = super.jsonInstance();
        xstream.aliasSystemAttribute(null, "class");
        xstream.aliasSystemAttribute(null, "resolves-to");
        return xstream;
    }





}