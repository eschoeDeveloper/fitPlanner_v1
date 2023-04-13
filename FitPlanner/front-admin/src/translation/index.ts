import {createI18n} from 'vue-i18n';

import kr from './kr.json';
import en from './en.json';
import es from './es.json';
import tr from './tr.json';

export const i18n = createI18n({
    locale: 'kr',
    messages: {kr, en, es, tr},
    fallbackLocale: 'kr',
    legacy: false
});
