    const { createProxyMiddleware } = require('http-proxy-middleware');
    const API_KEY = "FYinPI73hYosTCT6Uf4YrcuxtxltyQUg";

    module.exports = function (app) {
    app.use(
        '/api', // This can be any path you want to proxy, e.g. /api/1inch
        createProxyMiddleware({
        target: 'https://api.1inch.dev', // The target API (1inch)
        changeOrigin: true, // This allows the origin of the request to be changed to match the target
        pathRewrite: {
            '^/api': '', // Remove /api prefix before forwarding the request
        },
        onProxyReq: (proxyReq, req, res) => {
            // Add authorization headers if needed
            proxyReq.setHeader('Authorization', `Bearer ${API_KEY}`);
        },
        })
    );
    };