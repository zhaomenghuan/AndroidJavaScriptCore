package cn.com.agree.androidjsc;

import android.os.Handler;
import android.os.Looper;
import android.widget.TextView;

import cn.com.agree.abc.sdk.core.javascriptcore.JSContext;
import cn.com.agree.abc.sdk.core.javascriptcore.JSException;

public class ExampleContext extends JSContext implements IExampleContext {

	ExampleContext(TextView tv) throws JSException {
		super(IExampleContext.class);
		this.tv = tv;
	}
	
	private final TextView tv;
	private String log = "";

	public void clear() {
		log = "";
		tv.setText(log);
	}
	
	@Override
	public void log(String msg) {
		final String message = msg;
		Handler handler = new Handler(Looper.getMainLooper());
		handler.post(
				new Runnable()
				{
					@Override
					public void run()
					{
						log = log.concat(message);
						log = log.concat("\n");
						tv.setText(log);
					}
				}
		);
	}

}
