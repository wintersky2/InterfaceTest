'use client'

import Link from "next/link";
import { useEffect, useState } from "react";

export default function Datas() {

    const [datas, setDatas] = useState([]);
    const [content, setContent] = useState("");

    useEffect(() => {
        getDatas();
    }, []);

    const getDatas = async () => {
        await fetch('http://localhost:8080/api/v1/datas')
            .then(result => result.json())
            .then(resourse => setDatas(resourse.data))
    }

    const dataValue = event => {
        console.log(event.target.value);
        setContent(event.target.value);
    }

    const postData = async (event) => {
        event.preventDefault();

        const response = await fetch('http://localhost:8080/api/v1/datas', {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                content
            })
        })

        if (response.ok) {
            console.log(response);
            getDatas();
        }else{
            console.log(response);
        }
    }

    return (
        <>
            <ul>
                {datas.map(data => <li key={data.id}>{data.id}번 / <Link href={'/datas/'+data.id} >{data.content}</Link></li>)}
            </ul>
            <form>
                <span>data 등록하기</span>
                <input type="text" onChange={dataValue} />
                <button onClick={postData}>등록</button>
            </form>
        </>
    );
}