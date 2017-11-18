public class SongMetadata {
    public String artistSeed;
    public String songSeed;
    public String previewURL;

    public SongMetadata (String[] seeds, String initPreviewURL) {
        artistSeed = seeds[0];
        songSeed = seeds[1];
        previewURL = initPreviewURL;
    }
}
