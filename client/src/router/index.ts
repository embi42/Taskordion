import Vue from 'vue';
import VueRouter, { RouteConfig } from 'vue-router';
import Home from '../views/Home.vue';
import OktaVuePlugin from '@okta/okta-vue';
import BeerList from '@/components/BeerList.vue';

Vue.use(VueRouter);
Vue.use(OktaVuePlugin, {
  issuer: 'https://dev-961023.okta.com/oauth2/default',
  client_id: '0oacxq7dnIHfZq99D4x6',
  redirect_uri: window.location.origin + '/implicit/callback',
  scope: 'openid profile email',
});

const routes: RouteConfig[] = [
  {
    path: '/',
    name: 'Home',
    component: Home,
  },
  {
    path: '/about',
    name: 'About',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/About.vue'),
  },
  {
    path: '/beer-list',
    name: 'beer-list',
    component: BeerList,
    meta: {
      requiresAuth: true,
    },
  },
  { path: '/implicit/callback', component: OktaVuePlugin.handleCallback() },
];

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes,
});

router.beforeEach(Vue.prototype.$auth.authRedirectGuard());

export default router;
