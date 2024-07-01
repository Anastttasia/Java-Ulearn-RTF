public class Utils {
    private static ArrayList<Path> outPaths;
    private static ArrayList<Path> inPaths;
    private static String mainDirectory;
    private static String destDirectory;
	
	public static long calculateFolderSize(String path){
		long resultSize;

		File folder = new File(path);
		if (!folder.exists())
		{
			throw new IllegalArgumentException();
		}
		
		if (!folder.isDirectory())
		{
			return folder.length();
		}
		
		resultSize = 0;

		File[] files = folder.listFiles();
		for (File file : files)
		{
			if (file.isFile())
			{
				resultSize += file.length();
			}
			else if (file.isDirectory())
			{
				resultSize += calculateFolderSize(file.getPath());
			}

		}
		return resultSize;
    }


    public static void copyFolder(String sourceDirectory, String destinationDirectory){
        mainDirectory = sourceDirectory;
        destDirectory = destinationDirectory;
        outPaths = new ArrayList<>();
        inPaths = new ArrayList<>();
        File file = new File(sourceDirectory);
        if (!file.exists()) throw new IllegalArgumentException("Ошибка ввода");
        try {
            Files.walkFileTree(Paths.get(sourceDirectory), new MyFileVisitor());
			for (int i = 0; i < inPaths.size(); i++) {
				Files.createDirectories(outPaths.get(i));
                Files.copy(inPaths.get(i), outPaths.get(i), StandardCopyOption.REPLACE_EXISTING);

            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public static Path getPath(Path path) {
        String dir = path.toString().replace(mainDirectory, destDirectory);
        return Paths.get(dir);
    }

    public static class MyFileVisitor extends SimpleFileVisitor {

        public FileVisitResult visitFile(Object file, BasicFileAttributes attrs){
            Path path = (Path) file;
            inPaths.add(path);
            outPaths.add(getPath(path));
            return FileVisitResult.CONTINUE;
        }
    }
}
