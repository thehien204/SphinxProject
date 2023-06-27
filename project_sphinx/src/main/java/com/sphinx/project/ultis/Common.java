package com.sphinx.project.ultis;

import org.json.JSONObject;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.text.Normalizer;
import java.util.*;
import java.util.regex.Pattern;

@Component
public class Common {
    private static HashMap<Character, Double> hoTenMap = new HashMap<Character, Double>() {{
        put('A', 2.9104166663);
        put('B', 3.1749999996);
        put('C', 3.4395833329);
        put('D', 3.4395833329);
        put('E', 3.1749999996);
        put('F', 2.9104166663);
        put('G', 3.7041666662);
        put('H', 3.4395833329);
        put('I', 1.0583333332);
        put('Ι', 1.0583333332);
        put('J', 2.3812499997);
        put('K', 3.1749999996);
        put('L', 2.645833333);
        put('M', 3.9687499995);
        put('N', 3.4395833329);
        put('O', 3.7041666662);
        put('P', 3.1749999996);
        put('Q', 3.7041666662);
        put('R', 3.4395833329);
        put('S', 3.1749999996);
        put('T', 3.1749999996);
        put('U', 3.4395833329);
        put('V', 2.9104166663);
        put('W', 4.4979166661);
        put('X', 2.9104166663);
        put('Y', 3.1749999996);
        put('Ῠ', 3.1749999996);
        put('Z', 2.9104166663);
        put('a', 2.645833333);
        put('b', 1.3229166665);
        put('c', 2.3812499997);
        put('d', 2.645833333);
        put('e', 2.645833333);
        put('f', 1.3229166665);
        put('g', 2.645833333);
        put('h', 2.645833333);
        put('i', 1.0583333332);
        put('j', 1.0583333332);
        put('k', 2.3812499997);
        put('l', 1.0583333332);
        put('m', 3.7041666662);
        put('n', 2.645833333);
        put('o', 2.645833333);
        put('p', 2.645833333);
        put('q', 2.645833333);
        put('r', 1.5874999998);
        put('s', 2.3812499997);
        put('t', 1.3229166665);
        put('u', 2.645833333);
        put('v', 2.3812499997);
        put('w', 3.4395833329);
        put('x', 2.1166666664);
        put('y', 2.3812499997);
        put('ῠ', 2.3812499997);
        put('z', 2.1166666664);

        put('ơ', 3.1749999996);
        put('ư', 3.1749999996);
        put('Ơ', 3.9687499995);
        put('Ư', 3.9687499995);

        put('!', 1.5874999998);
        put('"', 1.5874999998);
        put('#', 2.645833333);
        put('$', 2.645833333);
        put('%', 4.2333333328);
        put('&', 3.1749999996);
        put('\'', 0.7937499999);
        put('’', 0.7937499999);
        put('(', 1.5874999998);
        put(')', 1.5874999998);
        put('*', 1.8520833331);
        put('+', 2.9104166663);
        put(',', 1.3229166665);
        put('-', 1.5874999998);
        put('.', 1.3229166665);
        put('/', 1.3229166665);
        put(';', 1.3229166665);
        put(':', 1.3229166665);
        put('<', 4.2333333328);
        put('=', 2.9104166663);
        put('>', 4.2333333328);
        put('?', 2.645833333);
        put('@', 4.7624999994);
        put('\\', 1.3229166665);
        put(']', 1.3229166665);
        put('[', 1.3229166665);
        put('^', 1.8520833331);
        put('_', 2.645833333);
        put('|', 1.5874999998);
        put('}', 1.5874999998);
        put('~', 2.9104166663);
        put(' ', 1.3229166665);
        put('0', 2.645833333);
        put('1', 2.3812499997);
        put('2', 2.645833333);
        put('3', 2.645833333);
        put('4', 2.645833333);
        put('5', 2.645833333);
        put('6', 2.645833333);
        put('7', 2.645833333);
        put('8', 2.645833333);
        put('9', 2.645833333);
        put('̀', 0.0);
        put('́', 0.0);
        put('̉', 0.0);
        put('̃', 0.0);
        put('̣', 0.0);

        put('ƀ', 2.3812499997);
        put('č', 2.3812499997);
        put('ĕ', 2.3812499997);
//        put('ê̆', 2.3812499997);
        put('̆', 0.0);
        put('ĭ', 1.3229166665);

        put('ñ', 2.3812499997);
        put('ŏ', 2.3812499997);
        put('ŭ', 2.3812499997);
        put('Ƀ', 2.9104166663);
        put('Č', 3.1749999996);
        put('Ĕ', 2.9104166663);
        put('Ῐ', 1.3229166665);
        put('Ĭ', 1.3229166665);
        put('Ñ', 2.9104166663);
        put('Ŏ', 3.1749999996);
        put('Ŭ', 2.9104166663);
    }};
    private static HashMap<Character, Double> quocTichMap = new HashMap<Character, Double>() {{
        put('A', 1.8520833331);
        put('B', 2.1166666664);
        put('C', 2.3812499997);
        put('D', 2.3812499997);
        put('E', 2.1166666664);
        put('F', 1.8520833331);
        put('G', 2.3812499997);
        put('H', 2.3812499997);
        put('I', 0.793749999900001);
        put('J', 1.5874999998);
        put('K', 2.1166666664);
        put('L', 1.8520833331);
        put('M', 2.3812499997);
        put('N', 2.3812499997);
        put('O', 2.3812499997);
        put('P', 2.1166666664);
        put('Q', 2.3812499997);
        put('R', 2.3812499997);
        put('S', 2.1166666664);
        put('T', 1.8520833331);
        put('U', 2.3812499997);
        put('V', 1.8520833331);
        put('W', 2.9104166663);
        put('X', 1.8520833331);
        put('Y', 1.8520833331);
        put('Z', 1.8520833331);
        put('a', 1.8520833331);
        put('b', 1.8520833331);
        put('c', 1.5874999998);
        put('d', 1.8520833331);
        put('e', 1.8520833331);
        put('f', 0.793749999900001);
        put('g', 1.8520833331);
        put('h', 1.8520833331);
        put('i', 0.793749999900001);
        put('j', 0.793749999900001);
        put('k', 1.5874999998);
        put('l', 0.793749999900001);
        put('m', 2.9104166663);
        put('n', 1.8520833331);
        put('o', 1.8520833331);
        put('p', 1.8520833331);
        put('q', 1.8520833331);
        put('r', 1.0583333332);
        put('s', 1.8520833331);
        put('t', 0.793749999900001);
        put('u', 1.8520833331);
        put('v', 1.3229166665);
        put('w', 2.3812499997);
        put('x', 1.3229166665);
        put('y', 1.3229166665);
        put('z', 1.3229166665);
        put('ơ', 2.1166666664);
        put('ư', 2.1166666664);
        put('Ơ', 2.645833333);
        put('Ư', 2.645833333);
        put('!', 0.7937499999);
        put('"', 1.0583333332);
        put('#', 1.8520833331);
        put('$', 1.8520833331);
        put('%', 2.9104166663);
        put('&', 2.1166666664);
        put('\'', 0.5291666666);
        put('(', 1.0583333332);
        put(')', 1.0583333332);
        put('*', 1.3229166665);
        put('+', 1.8520833331);
        put(',', 0.7937499999);
        put('-', 1.0583333332);
        put('.', 0.7937499999);
        put('/', 0.7937499999);
        put(';', 0.7937499999);
        put(':', 0.7937499999);
        put('<', 2.645833333);
        put('=', 1.8520833331);
        put('>', 2.645833333);
        put('?', 1.8520833331);
        put('@', 3.1749999996);
        put('\\', 0.7937499999);
        put(']', 0.7937499999);
        put('[', 0.7937499999);
        put('^', 1.3229166665);
        put('_', 1.8520833331);
        put('|', 0.7937499999);
        put('}', 1.0583333332);
        put('~', 1.8520833331);
        put(' ', 0.7937499999);
        put('0', 1.8520833331);
        put('1', 1.5874999998);
        put('2', 1.8520833331);
        put('3', 1.8520833331);
        put('4', 1.8520833331);
        put('5', 1.8520833331);
        put('6', 1.8520833331);
        put('7', 1.8520833331);
        put('8', 1.8520833331);
        put('9', 1.8520833331);
        put('̀', 0.0);
        put('́', 0.0);
        put('̉', 0.0);
    }};
    private static HashMap<Character, Double> queQuanMap = new HashMap<Character, Double>() {{
        put('A', 1.8520833331);
        put('B', 2.1166666664);
        put('C', 2.3812499997);
        put('D', 2.3812499997);
        put('E', 2.1166666664);
        put('F', 1.8520833331);
        put('G', 2.3812499997);
        put('H', 2.3812499997);
        put('I', 0.793749999900001);
        put('J', 1.5874999998);
        put('K', 2.1166666664);
        put('L', 1.8520833331);
        put('M', 2.3812499997);
        put('N', 2.3812499997);
        put('O', 2.3812499997);
        put('P', 2.1166666664);
        put('Q', 2.3812499997);
        put('R', 2.3812499997);
        put('S', 2.1166666664);
        put('T', 1.8520833331);
        put('U', 2.3812499997);
        put('V', 1.8520833331);
        put('W', 2.9104166663);
        put('X', 1.8520833331);
        put('Y', 1.8520833331);
        put('Z', 1.8520833331);
        put('a', 1.8520833331);
        put('b', 1.8520833331);
        put('c', 1.5874999998);
        put('d', 1.8520833331);
        put('e', 1.8520833331);
        put('f', 0.793749999900001);
        put('g', 1.8520833331);
        put('h', 1.8520833331);
        put('i', 0.793749999900001);
        put('j', 0.793749999900001);
        put('k', 1.5874999998);
        put('l', 0.793749999900001);
        put('m', 2.9104166663);
        put('n', 1.8520833331);
        put('o', 1.8520833331);
        put('p', 1.8520833331);
        put('q', 1.8520833331);
        put('r', 1.0583333332);
        put('s', 1.8520833331);
        put('t', 0.793749999900001);
        put('u', 1.8520833331);
        put('v', 1.3229166665);
        put('w', 2.3812499997);
        put('x', 1.3229166665);
        put('y', 1.3229166665);
        put('z', 1.3229166665);
        put('ơ', 2.1166666664);
        put('ư', 2.1166666664);
        put('Ơ', 2.645833333);
        put('Ư', 2.645833333);
        put('!', 0.7937499999);
        put('"', 1.0583333332);
        put('#', 1.8520833331);
        put('$', 1.8520833331);
        put('%', 2.9104166663);
        put('&', 2.1166666664);
        put('\'', 0.5291666666);
        put('(', 1.0583333332);
        put(')', 1.0583333332);
        put('*', 1.3229166665);
        put('+', 1.8520833331);
        put(',', 0.7937499999);
        put('-', 1.0583333332);
        put('.', 0.7937499999);
        put('/', 0.7937499999);
        put(';', 0.7937499999);
        put(':', 0.7937499999);
        put('<', 2.645833333);
        put('=', 1.8520833331);
        put('>', 2.645833333);
        put('?', 1.8520833331);
        put('@', 3.1749999996);
        put('\\', 0.7937499999);
        put(']', 0.7937499999);
        put('[', 0.7937499999);
        put('^', 1.3229166665);
        put('_', 1.8520833331);
        put('|', 0.7937499999);
        put('}', 1.0583333332);
        put('~', 1.8520833331);
        put(' ', 0.7937499999);
        put('0', 1.8520833331);
        put('1', 1.5874999998);
        put('2', 1.8520833331);
        put('3', 1.8520833331);
        put('4', 1.8520833331);
        put('5', 1.8520833331);
        put('6', 1.8520833331);
        put('7', 1.8520833331);
        put('8', 1.8520833331);
        put('9', 1.8520833331);
        put('̀', 0.0);
        put('́', 0.0);
        put('̉', 0.0);
        put('’', 0.7937499999);
    }};
    private static HashMap<Character, Double> noiThuongTruMap = new HashMap<Character, Double>() {{
        put('A', 2.3812499997);
        put('B', 2.645833333);
        put('C', 2.9104166663);
        put('D', 2.9104166663);
        put('E', 2.645833333);
        put('F', 2.3812499997);
        put('G', 2.9104166663);
        put('H', 2.645833333);
        put('I', 0.7937499999);
        put('Ι', 0.7937499999);
        put('J', 1.8520833331);
        put('K', 2.645833333);
        put('L', 2.1166666664);
        put('M', 2.9104166663);
        put('N', 2.645833333);
        put('O', 3.1749999996);
        put('P', 2.645833333);
        put('Q', 3.1749999996);
        put('R', 2.9104166663);
        put('S', 2.645833333);
        put('T', 2.3812499997);
        put('U', 2.645833333);
        put('V', 2.3812499997);
        put('W', 3.9687499995);
        put('X', 2.3812499997);
        put('Y', 2.3812499997);
        put('Z', 2.1166666664);
        put('a', 2.1166666664);
        put('b', 1.0583333332);
        put('c', 2.1166666664);
        put('d', 2.1166666664);
        put('e', 2.1166666664);
        put('f', 1.0583333332);
        put('g', 2.1166666664);
        put('h', 2.1166666664);
        put('i', 0.7937499999);
        put('j', 0.7937499999);
        put('k', 1.8520833331);
        put('l', 0.7937499999);
        put('m', 3.4395833329);
        put('n', 2.1166666664);
        put('o', 2.1166666664);
        put('p', 2.1166666664);
        put('q', 2.1166666664);
        put('r', 1.3229166665);
        put('s', 2.1166666664);
        put('t', 1.0583333332);
        put('u', 2.1166666664);
        put('v', 1.8520833331);
        put('w', 2.9104166663);
        put('x', 1.8520833331);
        put('y', 1.8520833331);
        put('z', 2.1166666664);
        put('ư', 2.645833333);
        put('Ư', 3.4395833329);
        put('ơ', 2.645833333);
        put('Ơ', 3.4395833329);
        put('!', 1.3229166665);
        put('"', 1.3229166665);
        put('#', 2.1166666664);
        put('$', 2.1166666664);
        put('%', 3.4395833329);
        put('&', 2.645833333);
        put('\'', 0.7937499999);
        put('’', 0.7937499999);
        put('(', 1.3229166665);
        put(')', 1.3229166665);
        put('*', 1.5874999998);
        put('+', 2.3812499997);
        put(',', 1.0583333332);
        put('-', 1.3229166665);
        put('.', 1.0583333332);
        put('/', 1.0583333332);
        put(';', 1.0583333332);
        put(':', 1.0583333332);
        put('<', 3.4395833329);
        put('=', 2.3812499997);
        put('>', 3.4395833329);
        put('?', 2.1166666664);
        put('@', 3.9687499995);
        put('\\', 1.0583333332);
        put(']', 1.0583333332);
        put('[', 1.0583333332);
        put('^', 1.3229166665);
        put('_', 2.1166666664);
        put('|', 0.7937499999);
        put('}', 1.3229166665);
        put('~', 2.3812499997);
        put(' ', 1.0583333332);
        put('0', 2.1166666664);
        put('1', 1.8520833331);
        put('2', 2.1166666664);
        put('3', 2.1166666664);
        put('4', 2.1166666664);
        put('5', 2.1166666664);
        put('6', 2.1166666664);
        put('7', 2.1166666664);
        put('8', 2.1166666664);
        put('9', 2.1166666664);
        put('̀', 0.0);
        put('́', 0.0);
        put('̉', 0.0);
        put('̃', 0.0);
        put('̣', 0.0);
        put('ῠ', 1.8520833331);
        put('Ῠ', 2.3812499997);

        put('ƀ', 2.1166666664);
        put('č', 2.1166666664);
        put('ĕ', 2.1166666664);
//        put('ê̆', 2.1166666664);
        put('̆', 0.0);
        put('ĭ', 0.7937499999);
        put('ñ', 2.1166666664);
        put('ŏ', 2.1166666664);
//        put('ơ̆', 2.645833333);
//        put('ô̆', 2.1166666664);
        put('ŭ', 2.1166666664);
//        put('ư̆', 2.645833333);
        put('Ƀ', 2.645833333);
        put('Č', 2.9104166663);
        put('Ĕ', 2.645833333);
        put('Ӗ', 2.645833333);
//        put('Ê̆', 2.645833333);
        put('Ĭ', 0.7937499999);
        put('Ῐ', 1.3229166665);
        put('Ñ', 2.645833333);
        put('Ŏ', 3.1749999996);
//        put('Ơ̆', 3.4395833329);
//        put('Ô̆', 3.1749999996);
        put('Ŭ', 2.645833333);

    }};
    private static HashMap<Character, Double> dacDiemNhanDangMap = new HashMap<Character, Double>() {{
        put('A', 1.8520833331);
        put('B', 2.1166666664);
        put('C', 2.3812499997);
        put('D', 2.3812499997);
        put('E', 2.1166666664);
        put('F', 1.8520833331);
        put('G', 2.3812499997);
        put('H', 2.3812499997);
        put('I', 0.7937499999);
        put('J', 1.5874999998);
        put('K', 2.1166666664);
        put('L', 1.8520833331);
        put('M', 2.3812499997);
        put('N', 2.3812499997);
        put('O', 2.3812499997);
        put('P', 2.1166666664);
        put('Q', 2.3812499997);
        put('R', 2.3812499997);
        put('S', 2.1166666664);
        put('T', 1.8520833331);
        put('U', 2.3812499997);
        put('V', 1.8520833331);
        put('W', 2.9104166663);
        put('X', 1.8520833331);
        put('Y', 1.8520833331);
        put('Z', 1.8520833331);
        put('a', 1.8520833331);
        put('b', 0.7937499999);
        put('c', 1.5874999998);
        put('d', 1.8520833331);
        put('e', 1.8520833331);
        put('f', 0.7937499999);
        put('g', 1.8520833331);
        put('h', 1.8520833331);
        put('i', 0.7937499999);
        put('j', 0.7937499999);
        put('k', 1.5874999998);
        put('l', 0.7937499999);
        put('m', 2.9104166663);
        put('n', 1.8520833331);
        put('o', 1.8520833331);
        put('p', 1.8520833331);
        put('q', 1.8520833331);
        put('r', 1.0583333332);
        put('s', 1.8520833331);
        put('t', 0.7937499999);
        put('u', 1.8520833331);
        put('v', 1.3229166665);
        put('w', 2.3812499997);
        put('x', 1.3229166665);
        put('y', 1.3229166665);
        put('z', 1.3229166665);
        put('ơ', 2.1166666664);
        put('ư', 2.1166666664);
        put('Ơ', 2.645833333);
        put('Ư', 2.645833333);
        put('!', 0.7937499999);
        put('"', 1.0583333332);
        put('#', 1.8520833331);
        put('$', 1.8520833331);
        put('%', 2.9104166663);
        put('&', 2.1166666664);
        put('\'', 0.5291666666);
        put('(', 1.0583333332);
        put(')', 1.0583333332);
        put('*', 1.3229166665);
        put('+', 1.8520833331);
        put(',', 0.7937499999);
        put('-', 1.0583333332);
        put('.', 0.7937499999);
        put('/', 0.7937499999);
        put(';', 0.7937499999);
        put(':', 0.7937499999);
        put('<', 2.645833333);
        put('=', 1.8520833331);
        put('>', 2.645833333);
        put('?', 1.8520833331);
        put('@', 3.1749999996);
        put('\\', 0.7937499999);
        put(']', 0.7937499999);
        put('[', 0.7937499999);
        put('^', 1.3229166665);
        put('_', 1.8520833331);
        put('|', 0.7937499999);
        put('}', 1.0583333332);
        put('~', 1.8520833331);
        put(' ', 0.7937499999);
        put('0', 1.8520833331);
        put('1', 1.5874999998);
        put('2', 1.8520833331);
        put('3', 1.8520833331);
        put('4', 1.8520833331);
        put('5', 1.8520833331);
        put('6', 1.8520833331);
        put('7', 1.8520833331);
        put('8', 1.8520833331);
        put('9', 1.8520833331);
        put('̀', 0.0);
        put('́', 0.0);
        put('̉', 0.0);
        put('̃', 0.0);
        put('̣', 0.0);
        put('ƀ', 1.5874999998);
        put('č', 1.5874999998);
        put('ĕ', 1.5874999998);
        put('̆', 0.0);
        put('ῠ', 2.3812499997);
        put('Ῠ', 3.1749999996);

//        put('ê̆', 1.5874999998);
        put('ĭ', 0.5291666666);
        put('ñ', 1.5874999998);
        put('ŏ', 1.5874999998);
//        put('ơ̆', 1.8520833331);
//        put('ô̆', 1.5874999998);
        put('ŭ', 1.5874999998);
//        put('ư̆', 1.8520833331);
        put('Ƀ', 1.8520833331);
        put('Č', 1.8520833331);
        put('Ĕ', 1.5874999998);
        put('Ӗ', 1.5874999998);
//        put('Ê̆', 1.5874999998);
        put('Ĭ', 0.5291666666);
        put('Ῐ', 0.5291666666);
        put('Ñ', 1.8520833331);
        put('Ŏ', 2.1166666664);
//        put('Ơ̆', 2.3812499997);
//        put('Ô̆', 2.1166666664);
        put('Ŭ', 1.8520833331);
//        put('Ư̆', 2.3812499997);
    }};

    private static Common common;

    public static Common getInstanCommon() {
        if (common == null) {
            common = new Common();
        }
        return common;
    }

    public enum INFOTYPE {
        HO_TEN_1,
        HO_TEN_2,
        QUOC_TICH,
        QUE_QUAN_1,
        QUE_QUAN_2,
        NOI_THUONG_TRU_1,
        NOI_THUONG_TRU_2,
        DAC_DIEM_NHAN_DANG_1,
        DAC_DIEM_NHAN_DANG_2
    }

    private static final Double MAX_HO_TEN_1 = 51.32916666;
    private static final Double MAX_HO_TEN_2 = 79.63958332;
    private static final Double MAX_QUOC_TICH = 18.78541666;
    private static final Double MAX_QUE_QUAN_1 = 45.24374999;
    private static final Double MAX_QUE_QUAN_2 = 79.63958332;
    private static final Double MAX_NOI_THUONG_TRU_1 = 33.86666666;
    private static final Double MAX_NOI_THUONG_TRU_2 = 79.63958332;
    private static final Double MAX_DAC_DIEM_NHAN_DANG_1 = 50.79999999;
    private static final Double MAX_DAC_DIEM_NHAN_DANG_2 = 50.79999999;

    public Common() {
    }

    private static char[] SOURCE_CHARACTERS = {
        'À', 'Á', 'Â', 'Ã', 'È', 'É', 'Ê', 'Ì', 'Í', 'Ò', 'Ó', 'Ô', 'Õ', 'Ù', 'Ú', 'Ý', 'Ý', 'à', 'á', 'â',
        'ã', 'è', 'é', 'ê', 'ì', 'í', 'ò', 'ó', 'ô', 'õ', 'ù', 'ú', 'ý', 'Ă', 'ă', 'Đ', 'đ', 'Ĩ', 'ĩ', 'Ũ',
        'ũ', 'Ơ', 'ơ', 'Ư', 'ư', 'Ạ', 'ạ', 'Ả', 'ả', 'Ấ', 'ấ', 'Ầ', 'ầ', 'Ẩ', 'ẩ', 'Ẫ', 'ẫ', 'Ậ', 'ậ', 'Ắ',
        'ắ', 'Ằ', 'ằ', 'Ẳ', 'ẳ', 'Ẵ', 'ẵ', 'Ặ', 'ặ', 'Ẹ', 'ẹ', 'Ẻ', 'ẻ', 'Ẽ', 'ẽ', 'Ế', 'ế', 'Ề', 'ề', 'Ể',
        'ể', 'Ễ', 'ễ', 'Ệ', 'ệ', 'Ỉ', 'ỉ', 'Ị', 'ị', 'Ọ', 'ọ', 'Ỏ', 'ỏ', 'Ố', 'ố', 'Ồ', 'ồ', 'Ổ', 'ổ', 'Ỗ',
        'ỗ', 'Ộ', 'ộ', 'Ớ', 'ớ', 'Ờ', 'ờ', 'Ở', 'ở', 'Ỡ', 'ỡ', 'Ợ', 'ợ', 'Ụ', 'ụ', 'Ủ', 'ủ', 'Ứ', 'ứ', 'Ừ',
        'ừ', 'Ử', 'ử', 'Ữ', 'ữ', 'Ự', 'ự', 'Ỳ', 'ỳ', 'Ỵ', 'ỵ', 'Ỷ', 'ỷ', 'Ỹ', 'ỹ', 'Ӗ', 'Ῐ', 'ῐ', 'Ῠ', 'ῠ',
        'ӗ', 'Ў', 'Ᾰ', 'Ň', 'Ě', 'ň', 'ě', 'Ŏ', 'ŏ', 'Ӑ', 'ӑ', 'У'
    };
    private static final char[] DESTINATION_CHARACTERS = {
        'A', 'A', 'A', 'A', 'E', 'E', 'E', 'I', 'I', 'O', 'O', 'O', 'O', 'U', 'U', 'Y', 'Y', 'a', 'a', 'a',
        'a', 'e', 'e', 'e', 'i', 'i', 'o', 'o', 'o', 'o', 'u', 'u', 'y', 'A', 'a', 'D', 'd', 'I', 'i', 'U',
        'u', 'Ơ', 'ơ', 'Ư', 'ư', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A',
        'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'E', 'e', 'E', 'e', 'E', 'e', 'E', 'e', 'E', 'e', 'E',
        'e', 'E', 'e', 'E', 'e', 'I', 'i', 'I', 'i', 'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o', 'O',
        'o', 'O', 'o', 'Ơ', 'ơ', 'Ơ', 'ơ', 'Ơ', 'ơ', 'Ơ', 'ơ', 'Ơ', 'ơ', 'U', 'u', 'U', 'u', 'Ư', 'ư', 'Ư',
        'ư', 'Ư', 'ư', 'Ư', 'ư', 'Ư', 'ư', 'Y', 'y', 'Y', 'y', 'Y', 'y', 'Y', 'y', 'E', 'I', 'i', 'Y', 'y',
        'e', 'Y', 'A', 'N', 'E', 'n', 'e', 'O', 'o', 'A', 'a', 'Y'
    };

    public static char removeAccent(char ch) {
        int index = Arrays.binarySearch(SOURCE_CHARACTERS, ch);
        if (index >= 0) {
            ch = DESTINATION_CHARACTERS[index];
        }
        return ch;
    }

    public static String removeAccent(String str) {
        StringBuilder sb = new StringBuilder(str);
        for (int i = 0; i < sb.length(); i++) {
            sb.setCharAt(i, removeAccent(sb.charAt(i)));
        }
        return sb.toString();
    }

    private static String unAccent(String s) {
        String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(temp).replaceAll("").replace("Đ", "D").replace("đ", "d");
    }

    private static Set<Character> removeDuplicates(String text) {
        char[] chars = text.toCharArray();
        Set<Character> charSet = new LinkedHashSet<>();
        for (char c : chars) {
            charSet.add(c);
        }
        return charSet;
    }

    private static Double checkMaxSizeCccdInfoExe(String info, HashMap<Character, Double> map) throws Exception {
        info = removeAccent(info);
        info = unAccent(info);
        info = info.replace("Đ", "D");
        info = info.replace("đ", "d");
        info = info.replace("У", "Y");
        info = info.replace("Е", "E");
        info = info.replace("А", "A");
        System.out.println(info);
        Set<Character> charSet = removeDuplicates(info);
        Double size = 0.0;
        for (char item : charSet) {
            int occurance = StringUtils.countOccurrencesOf(info, String.valueOf(item));
            if (map.containsKey(item)) {
                Double itemLeng = map.get(item);
                size += occurance * itemLeng;
            } else {
                throw new Exception(" ký tự '" + item + "' không tồn tại!");
            }
        }
        System.out.println(size);
        return size;
    }


    public static ResponseEntity<String> callPostApi(String oauthString, String url, HttpMethod method, String json) throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        JSONObject jsonObj = new JSONObject(json);
        HttpHeaders headers2 = new HttpHeaders();
        headers2.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<String> entity = new HttpEntity<String>(jsonObj.toString(), headers2);
        ResponseEntity<String> resTemp = restTemplate.exchange(url, method, entity, String.class);
        return resTemp;
    }

    public static String callApiBodyRaw(String oauthString, String url, HttpMethod method, String json) throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        JSONObject jsonObj = new JSONObject(json);
        HttpHeaders headers2 = new HttpHeaders();
        headers2.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<String> entity = new HttpEntity<String>(jsonObj.toString(), headers2);
        ResponseEntity<String> resTemp = restTemplate.exchange(url, method, entity, String.class);
        return resTemp.getBody();
    }




    public static String postDataByApiBodyUtf8WithFormData(String url, String body, Pageable pageable) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        JSONObject obj = new JSONObject(body);
        String content = obj.optString("content");
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("size", String.valueOf(pageable.getPageSize()));
        map.add("page", String.valueOf(pageable.getPageNumber()));
        map.add("content", content);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
        ResponseEntity<String> result = restTemplate.postForEntity(url, request, String.class);
        return result.getBody();
    }


    public static boolean callApiABIS(String url, String requestJson, String tokenABIS) throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer " + tokenABIS);
        HttpEntity<String> entity = new HttpEntity<String>(requestJson, headers);
        ResponseEntity<String> answer = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
        if (answer.getStatusCode().value() != 200) {
            return false;
        }
        return true;
    }



}
