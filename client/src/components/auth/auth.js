export default function (SpecificComponent, option, adminRoute = null) {
  // null -> 아무나
  // true -> 로그인한 유저만
  // false -> 로그인한 유저는 출입 불가능

  // adminRoute
  // true -> 어드민만 출입 가능

  function AuthenticationCheck(props) {
    const dispatch = useDispatch();

    useEffect(() => {
      dispatch(auth()).then((response) => {
        if (!response.payload.isAuth) {
          if (option) {
            props.history.push("/login");
          }
        } else {
          // 로그인 한 상태
          if (adminRoute && !response.payload.isAdmin) {
            props.history.push("/");
          } else {
            if (!option) {
              props.history.push("/");
            }
          }
        }
      });
    }, []);

    return <SpecificComponent />;
  }

  return AuthenticationCheck;
}
