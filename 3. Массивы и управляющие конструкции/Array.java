

public class Constants {
	public static final float GEN_MIN_TEMP = 32f;
	public static final float GEN_MAX_TEMP = 40f;

	public static final float HEALTH_MIN_TEMP = 36.2f;
	public static final float HEALTH_MAX_TEMP = 36.9f;

	public static final int NOT_COUNTED_HEALTHY = -1;
	public static final double NOT_COUNTED_AVG = 0.0;
}

public class Main {

    public static void main(String[] args) {
        //TODO напишите консольное приложение для работы с классом больница, учитывая, что сгенерированные и вычисленные переменные,
		// нельзя будет менять.
		Hospital host = new Hospital(12);
        System.out.printf(host.getReport());
    }
}

public class Hospital {
	
	int patientsCount;
	float[] patientsTemperatures;
	int countHealthyPatients;
	boolean isCountedHealthy;
	String temperatureAsStr;
	double avgTemp;
	String reportTemp;

    public Hospital(int patientsCount) {
        //TODO создание больницы с указанным кол-вом пациентов
		// если значение не верное, бросать IllegalArgumentException с помощью метода throw new <Exception.class>
		try {
			if (patientsCount < 1) throw new IllegalArgumentException();
		}
		catch (IllegalArgumentException e) {
				throw new IllegalArgumentException();
		}
		this.patientsCount = patientsCount;
		this.patientsTemperatures = null;
		this.countHealthyPatients = Constants.NOT_COUNTED_HEALTHY;
		this.temperatureAsStr = null;
		this.avgTemp = Constants.NOT_COUNTED_AVG;
		this.reportTemp = null;
    }

    //нужно кол-во пациентов
	public float[] generatePatientsTemperatures() {
		//TODO реализовать генерацию массива температур от 32 до 40 градусов
		if (this.patientsTemperatures == null) {
			this.patientsTemperatures = new float[this.patientsCount];
			for (int i = 0; i < this.patientsTemperatures.length; i++){ 
				this.patientsTemperatures[i] = (float) Math.random() * (Constants.GEN_MAX_TEMP - Constants.GEN_MIN_TEMP) + Constants.GEN_MIN_TEMP;
			}
		}

	    return this.patientsTemperatures;
    }

    //нужна генерация температур
    public int getCountHealthy() {
        //TODO реализовать подсчёт кол-ва здоровых(36,2 < x < 36,9) в сгенерированном массиве температур
        // если метод уже был вызван ранее, не выполнять подсчет снова, а возращать текущее вычисленное значение
		// если генерация температур не была выполнена, вызвать метод генерации

		if (this.countHealthyPatients == Constants.NOT_COUNTED_HEALTHY){
			this.generatePatientsTemperatures();

			this.countHealthyPatients = 0;
			for (int i = 0; i < this.patientsTemperatures.length; i++) {
				float patientTemp = this.patientsTemperatures[i];
				boolean isHealthyPatient = Constants.HEALTH_MAX_TEMP > patientTemp && Constants.HEALTH_MIN_TEMP < patientTemp;
				if(isHealthyPatient) {
					this.countHealthyPatients += 1;
				}
			}
		}

		return this.countHealthyPatients;
    }

    //нужна генерация температур
    public String getTemperaturesToString() {
        //TODO реализовать получение массива температур в виде строки <double>, <double>, <double>
        // если метод уже был вызван ранее, не выполнять подсчет снова, а возращать текущее вычисленное значение
		// если генерация температур не была выполнена, вызвать метод генерации
		if (this.temperatureAsStr == null) {
			this.generatePatientsTemperatures();
			String finalStr = "";
			for (int i = 0; i < this.patientsTemperatures.length; i++) {
				if (i == 0) {
					finalStr = finalStr + String.format("%.1f", this.patientsTemperatures[i]);
				}
				else {
					finalStr = finalStr + " " + String.format("%.1f", this.patientsTemperatures[i]);
				}
			}
			this.temperatureAsStr = finalStr;
		}

        return this.temperatureAsStr;
    }

    //Нужна генерация температур
    public double getAverageTemp() {
        //TODO реализовать получение средней температуры из массива
        // если метод уже был вызван ранее, не выполнять подсчет снова, а возращать текущее вычисленное значение
		// если генерация температур не была выполнена, вызвать метод генерации
		if (this.avgTemp == Constants.NOT_COUNTED_AVG) {
			this.generatePatientsTemperatures();
			double sum = 0.0;
 
			for (int i = 0; i < this.patientsTemperatures.length; i++) {
				sum += this.patientsTemperatures[i];
			}
        	this.avgTemp = sum / patientsCount;
		}
		
		return this.avgTemp;
    }


    //нужны температуры, средняя температура и кол-во здоровых
    public String getReport() {
        //TODO реализовать получение отчёта в формате приложенном ниже
        // если метод уже был вызван ранее, не выполнять подсчет снова, а возращать текущее вычисленное значение
        // если все необходимые переменные не были получены ранее, вызвать все методы необходимые для получения отчёта
        /*TODO Формат вывода:
           Температуры пациентов: 37,5 36,9 38,2 33,5 32,2
           Средняя температура: 35,67
           Количество здоровых: 1
		   */
		if (this.reportTemp == null) {
			this.reportTemp = String.format("Температуры пациентов: %s\nСредняя температура: %.2f\nКоличество здоровых: %d", this.getTemperaturesToString(), this.getAverageTemp(), this.getCountHealthy());
		}

        return this.reportTemp;
    }
}