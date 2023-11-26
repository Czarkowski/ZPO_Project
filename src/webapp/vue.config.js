module.exports = {
    outputDir: 'src/main/resources/static', // Lub dowolna inna lokalizacja w projekcie Spring Boot
    devServer: {
        proxy: {
            '/api': {
                target: 'http://localhost:8080', // Adres Twojej aplikacji Spring Boot
                ws: true,
                changeOrigin: true
            }
        }
    }
}