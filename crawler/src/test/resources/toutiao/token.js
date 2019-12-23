function s(t, e) {
    var a = (65535 & t) + (65535 & e);
    return (t >> 16) + (e >> 16) + (a >> 16) << 16 | 65535 & a
}
function o(t, e) {
    return t << e | t >>> 32 - e
}
function r(t, e, a, n, i, r) {
    return s(o(s(s(e, t), s(n, r)), i), a)
}
function u(t, e, a, n, i, s, o) {
    return r(e & a | ~e & n, t, e, i, s, o)
}
function c(t, e, a, n, i, s, o) {
    return r(e & n | a & ~n, t, e, i, s, o)
}
function d(t, e, a, n, i, s, o) {
    return r(e ^ a ^ n, t, e, i, s, o)
}
function l(t, e, a, n, i, s, o) {
    return r(a ^ (e | ~n), t, e, i, s, o)
}
function f(t, e) {
    t[e >> 5] |= 128 << e % 32,
        t[14 + (e + 64 >>> 9 << 4)] = e;
    var a, n, i, o, r, f = 1732584193, p = -271733879, _ = -1732584194, m = 271733878;
    for (a = 0; a < t.length; a += 16)
        n = f,
            i = p,
            o = _,
            r = m,
            f = u(f, p, _, m, t[a], 7, -680876936),
            m = u(m, f, p, _, t[a + 1], 12, -389564586),
            _ = u(_, m, f, p, t[a + 2], 17, 606105819),
            p = u(p, _, m, f, t[a + 3], 22, -1044525330),
            f = u(f, p, _, m, t[a + 4], 7, -176418897),
            m = u(m, f, p, _, t[a + 5], 12, 1200080426),
            _ = u(_, m, f, p, t[a + 6], 17, -1473231341),
            p = u(p, _, m, f, t[a + 7], 22, -45705983),
            f = u(f, p, _, m, t[a + 8], 7, 1770035416),
            m = u(m, f, p, _, t[a + 9], 12, -1958414417),
            _ = u(_, m, f, p, t[a + 10], 17, -42063),
            p = u(p, _, m, f, t[a + 11], 22, -1990404162),
            f = u(f, p, _, m, t[a + 12], 7, 1804603682),
            m = u(m, f, p, _, t[a + 13], 12, -40341101),
            _ = u(_, m, f, p, t[a + 14], 17, -1502002290),
            p = u(p, _, m, f, t[a + 15], 22, 1236535329),
            f = c(f, p, _, m, t[a + 1], 5, -165796510),
            m = c(m, f, p, _, t[a + 6], 9, -1069501632),
            _ = c(_, m, f, p, t[a + 11], 14, 643717713),
            p = c(p, _, m, f, t[a], 20, -373897302),
            f = c(f, p, _, m, t[a + 5], 5, -701558691),
            m = c(m, f, p, _, t[a + 10], 9, 38016083),
            _ = c(_, m, f, p, t[a + 15], 14, -660478335),
            p = c(p, _, m, f, t[a + 4], 20, -405537848),
            f = c(f, p, _, m, t[a + 9], 5, 568446438),
            m = c(m, f, p, _, t[a + 14], 9, -1019803690),
            _ = c(_, m, f, p, t[a + 3], 14, -187363961),
            p = c(p, _, m, f, t[a + 8], 20, 1163531501),
            f = c(f, p, _, m, t[a + 13], 5, -1444681467),
            m = c(m, f, p, _, t[a + 2], 9, -51403784),
            _ = c(_, m, f, p, t[a + 7], 14, 1735328473),
            p = c(p, _, m, f, t[a + 12], 20, -1926607734),
            f = d(f, p, _, m, t[a + 5], 4, -378558),
            m = d(m, f, p, _, t[a + 8], 11, -2022574463),
            _ = d(_, m, f, p, t[a + 11], 16, 1839030562),
            p = d(p, _, m, f, t[a + 14], 23, -35309556),
            f = d(f, p, _, m, t[a + 1], 4, -1530992060),
            m = d(m, f, p, _, t[a + 4], 11, 1272893353),
            _ = d(_, m, f, p, t[a + 7], 16, -155497632),
            p = d(p, _, m, f, t[a + 10], 23, -1094730640),
            f = d(f, p, _, m, t[a + 13], 4, 681279174),
            m = d(m, f, p, _, t[a], 11, -358537222),
            _ = d(_, m, f, p, t[a + 3], 16, -722521979),
            p = d(p, _, m, f, t[a + 6], 23, 76029189),
            f = d(f, p, _, m, t[a + 9], 4, -640364487),
            m = d(m, f, p, _, t[a + 12], 11, -421815835),
            _ = d(_, m, f, p, t[a + 15], 16, 530742520),
            p = d(p, _, m, f, t[a + 2], 23, -995338651),
            f = l(f, p, _, m, t[a], 6, -198630844),
            m = l(m, f, p, _, t[a + 7], 10, 1126891415),
            _ = l(_, m, f, p, t[a + 14], 15, -1416354905),
            p = l(p, _, m, f, t[a + 5], 21, -57434055),
            f = l(f, p, _, m, t[a + 12], 6, 1700485571),
            m = l(m, f, p, _, t[a + 3], 10, -1894986606),
            _ = l(_, m, f, p, t[a + 10], 15, -1051523),
            p = l(p, _, m, f, t[a + 1], 21, -2054922799),
            f = l(f, p, _, m, t[a + 8], 6, 1873313359),
            m = l(m, f, p, _, t[a + 15], 10, -30611744),
            _ = l(_, m, f, p, t[a + 6], 15, -1560198380),
            p = l(p, _, m, f, t[a + 13], 21, 1309151649),
            f = l(f, p, _, m, t[a + 4], 6, -145523070),
            m = l(m, f, p, _, t[a + 11], 10, -1120210379),
            _ = l(_, m, f, p, t[a + 2], 15, 718787259),
            p = l(p, _, m, f, t[a + 9], 21, -343485551),
            f = s(f, n),
            p = s(p, i),
            _ = s(_, o),
            m = s(m, r);
    return [f, p, _, m]
}
function p(t) {
    var e, a = "", n = 32 * t.length;
    for (e = 0; e < n; e += 8)
        a += String.fromCharCode(t[e >> 5] >>> e % 32 & 255);
    return a
}
function _(t) {
    var e, a = [];
    for (a[(t.length >> 2) - 1] = void 0,
             e = 0; e < a.length; e += 1)
        a[e] = 0;
    var n = 8 * t.length;
    for (e = 0; e < n; e += 8)
        a[e >> 5] |= (255 & t.charCodeAt(e / 8)) << e % 32;
    return a
}
function m(t) {
    return p(f(_(t), 8 * t.length))
}
function v(t, e) {
    var a, n, i = _(t), s = [], o = [];
    for (s[15] = o[15] = void 0,
         i.length > 16 && (i = f(i, 8 * t.length)),
             a = 0; a < 16; a += 1)
        s[a] = 909522486 ^ i[a],
            o[a] = 1549556828 ^ i[a];
    return n = f(s.concat(_(e)), 512 + 8 * e.length),
        p(f(o.concat(n), 640))
}
function h(t) {
    var e, a, n = "0123456789abcdef", i = "";
    for (a = 0; a < t.length; a += 1)
        e = t.charCodeAt(a),
            i += n.charAt(e >>> 4 & 15) + n.charAt(15 & e);
    return i
}
function g(t) {
    return unescape(encodeURIComponent(t))
}
function y(t) {
    return m(g(t))
}
function C(t) {
    return h(y(t))
}
function S(t, e) {
    return v(g(t), g(e))
}
function I(t, e) {
    return h(S(t, e))
}
function P(t, e, a) {
    return e ? a ? S(e, t) : I(e, t) : a ? y(t) : C(t)
}


function getToken(s) {
    var e = arguments.length > 1 && void 0 !== arguments[1] ? arguments[1] : "zd2019@@1157";
    return P(P(s) + e);
}