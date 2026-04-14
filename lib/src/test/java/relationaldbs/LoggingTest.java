package relationaldbs;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;

/*
 * Level ALL: El nivel all, como su propio nombre indica, ejecuta y muestra todo en la consola
 * Level DEBUG: Al utilzar este nivel no se ejecuta la division de los números enteros
 * Level ERROR: Con este nivel solo se ejecuta el Logger.error, solo muestra en la consola el resultado de ERROR, lo demás no
 * Level INFO: El nivel info solo ejecuta los logger info, warn y error, trace y debug no se ejecutan y no se muestran en la consola, la división tampoco
 * Level OFF: Utilizando este nivel no se ejecuta nada y no se muestra nada en la consola
 * Level TRACE: Este nivel ejecuta todo y se muestran en la consola todos los Logger, la división también
 * Level WARN: El nivel warn solo ejecuta los logger warn y error, los demás no se muestran en la consola
 * 
 * 
 * @author Alexander
 * 4 feb 2026
 */
public class LoggingTest {
	
		//1. Get the logger as usual, but the cast it to the Logback implementation
		static Logger logger = (Logger) LoggerFactory.getLogger(LoggingTest.class);	

	public static void main(String[] args) {	
			
		logger.setLevel(Level.ALL);
//		logger.setLevel(Level.DEBUG);
//		logger.setLevel(Level.ERROR);
//		logger.setLevel(Level.INFO);
//		logger.setLevel(Level.OFF);
//		logger.setLevel(Level.TRACE);
//		logger.setLevel(Level.WARN);

		division(24, 2);
				
	}

	private static void division(int i, int j) {
		
		logger.trace("Trace log: You entered the method 'division'");
		logger.debug("Debug log: We use this level of log to debug our program");
		logger.info("Info log: This log is used to show normal information that happens in our program");
		logger.warn("Warn log: the warn log is used to show potential");
		logger.error("Error log: is used for logging problems and errors that concern");
	
		try {
			int result = i / j;
			logger.trace("result = " + result);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
	}

}
